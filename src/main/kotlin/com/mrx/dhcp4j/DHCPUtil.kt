@file:Suppress("UNUSED")

package com.mrx.dhcp4j

import com.mrx.dhcp4j.DHCPConstants.Companion.BROAD_CAST
import org.dhcp4java.DHCPBadPacketException
import org.dhcp4java.DHCPOption
import org.dhcp4java.DHCPPacket
import org.pcap4j.packet.*
import org.pcap4j.packet.namednumber.EtherType
import org.pcap4j.packet.namednumber.IpNumber
import org.pcap4j.packet.namednumber.IpVersion
import org.pcap4j.packet.namednumber.UdpPort
import org.pcap4j.util.MacAddress
import java.net.Inet4Address
import java.net.InetAddress

object DHCPUtil {
    class DHCPPacketBuilder {
        private val packet = DHCPPacket()
        private val options = HashSet<DHCPOption>()
        private val requestList = HashSet<Byte>()
        private lateinit var myIP: InetAddress
        private lateinit var myMac: MacAddress

        fun comment(comment: String): DHCPPacketBuilder {
            packet.comment = comment
            return this
        }


        fun op(op: DHCPConstants.OP): DHCPPacketBuilder {
            packet.op = op.value
            return this
        }

        fun htype(hType: DHCPConstants.HType): DHCPPacketBuilder {
            packet.htype = hType.value
            return this
        }

        fun hlen(hlen: DHCPConstants.HLen): DHCPPacketBuilder {
            packet.hlen = hlen.value
            return this
        }

        fun hops(hops: Byte): DHCPPacketBuilder {
            packet.hops = hops
            return this
        }

        fun xid(xid: Int): DHCPPacketBuilder {
            packet.xid = xid
            return this
        }

        fun secs(secs: Short): DHCPPacketBuilder {
            packet.secs = secs
            return this
        }

        fun flags(flags: Short): DHCPPacketBuilder {
            packet.flags = flags
            return this
        }

        fun ciaddr(ciaddr: InetAddress): DHCPPacketBuilder {
            packet.ciaddr = ciaddr
            this.myIP = ciaddr
            return this
        }

        fun yiaddr(yiaddr: InetAddress): DHCPPacketBuilder {
            packet.yiaddr = yiaddr
            return this
        }

        fun siaddr(siaddr: InetAddress): DHCPPacketBuilder {
            packet.siaddr = siaddr
            return this
        }

        fun giaddr(giaddr: InetAddress): DHCPPacketBuilder {
            packet.giaddr = giaddr
            return this
        }

        fun chaddr(chaddr: MacAddress): DHCPPacketBuilder {
            packet.chaddr = chaddr.address
            this.myMac = chaddr
            return this
        }

        fun sname(sname: String): DHCPPacketBuilder {
            packet.sname = sname
            return this
        }

        fun file(file: String): DHCPPacketBuilder {
            packet.file = file
            return this
        }

        fun setOptions(options: Array<DHCPOption>): DHCPPacketBuilder {
            this.options.addAll(options)
            return this
        }

        fun addOption(option: DHCPOption): DHCPPacketBuilder {
            this.options.add(option)
            return this
        }

        fun setMessageType(messageType: DHCPConstants.Message): DHCPPacketBuilder {
            return this.addOption(
                DHCPOption(
                    DHCPConstants.Options.DHO_DHCP_MESSAGE_TYPE.value,
                    messageType.value.toByteArray()
                )
            )
        }

        fun setHostName(hostName: String): DHCPPacketBuilder {
            return this.addOption(
                DHCPOption(
                    DHCPConstants.Options.DHO_HOST_NAME.value,
                    hostName.toByteArray()
                )
            )
        }

        fun setFQDN(fqdn: String): DHCPPacketBuilder {
            return this.addOption(
                DHCPOption(
                    DHCPConstants.Options.DHO_FQDN.value,
                    mutableListOf<Byte>(0x00, 0x00, 0x00).also {
                        it.addAll(fqdn.toByteArray().toList())
                    }
                        .toTypedArray()
                        .toByteArray()
                )
            )
        }

        fun addRequestList(option: DHCPConstants.Options): DHCPPacketBuilder {
            this.requestList.add(option.value)
            return this
        }

        fun setRequestList(requestList: ByteArray): DHCPPacketBuilder {
            requestList.forEach {
                this.requestList.add(it)
            }
            return this
        }

        private fun Byte.toByteArray() = byteArrayOf(this)

        fun build(serverMac: MacAddress = MacAddress.ETHER_BROADCAST_ADDRESS): EthernetPacket {
            if (!this::myIP.isInitialized || !this::myMac.isInitialized) {
                throw DHCPBadPacketException("请设置 自己的 IP 和 自己的 MAC")
            }
            this.options.add(
                DHCPOption(
                    DHCPConstants.Options.DHO_DHCP_PARAMETER_REQUEST_LIST.value,
                    this.requestList.toByteArray()
                )
            )
            packet.setOptions(this.options)

            val buffer = packet.serialize()
            val dhcp = UnknownPacket.newPacket(buffer, 0, buffer.size)
                .builder

            val udp = UdpPacket.Builder()
                .srcPort(UdpPort.BOOTPC)
                .dstPort(UdpPort.BOOTPS)
                .srcAddr(this.myIP)
                .dstAddr(BROAD_CAST)
                .payloadBuilder(dhcp)
                .correctLengthAtBuild(true)
                .correctChecksumAtBuild(true)

            val ip = IpV4Packet.Builder()
                .version(IpVersion.IPV4)
                .tos(IpV4Rfc1349Tos.newInstance(0x0))
                .ttl(64)
                .protocol(IpNumber.UDP)
                .srcAddr(this.myIP as Inet4Address)
                .dstAddr(BROAD_CAST as Inet4Address)
                .paddingAtBuild(true)
                .correctLengthAtBuild(true)
                .correctChecksumAtBuild(true)
                .payloadBuilder(udp)

            val eth = EthernetPacket.Builder()
                .type(EtherType.IPV4)
                .srcAddr(this.myMac)
                .dstAddr(serverMac)
                .paddingAtBuild(true)
                .payloadBuilder(ip)

            return eth.build()
        }
    }
}