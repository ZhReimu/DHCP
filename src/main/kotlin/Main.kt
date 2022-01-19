import com.mrx.XLog
import com.mrx.dhcp4j.DHCPConstants
import com.mrx.dhcp4j.DHCPUtil
import org.pcap4j.core.PcapNetworkInterface
import org.pcap4j.core.Pcaps
import org.pcap4j.util.MacAddress
import java.net.InetAddress
import java.util.*


object Main {

    private val logger = XLog.getLogger(this::class.java)
    private val MY_IP = InetAddress.getByName("192.168.2.160")
    private val FAKE_IP = InetAddress.getByName("192.168.250.102")
    private val MY_MAC = MacAddress.getByName("20-0D-B0-47-41-D2")
    private val FAKE_MAC = MacAddress.getByName("90-09-90-97-91-66")
    private val TEST_SERVER_MAC = MacAddress.getByName("00-0C-29-A7-F5-42")

    @JvmStatic
    fun main(args: Array<String>) {
        sendDiscover()
        Thread.sleep(2000)
        sendRequest()
    }

    private fun sendDiscover() {
        val eth = DHCPUtil.DHCPPacketBuilder()
            .op(DHCPConstants.OP.BOOT_REQUEST)
            .htype(DHCPConstants.HType.ETHER)
            .hlen(DHCPConstants.HLen.ETHER)
            .xid(Random().nextInt())
            // Discover 时需要使用 ANY_ADDRESS 与 MY_MAC
            .ciaddr(DHCPConstants.ANY_ADDRESS)
            .chaddr(FAKE_MAC)

            .setMessageType(DHCPConstants.Message.DHCP_DISCOVER)
            .setHostName("TEST")
            .setFQDN("TEST FQ")
            .addRequestList(DHCPConstants.Options.DHO_SUBNET_MASK)
            .addRequestList(DHCPConstants.Options.DHO_ROUTERS)
            .addRequestList(DHCPConstants.Options.DHO_DOMAIN_NAME_SERVERS)
            .addRequestList(DHCPConstants.Options.DHO_DOMAIN_NAME)
            .addRequestList(DHCPConstants.Options.DHO_PERFORM_MASK_DISCOVERY)
            .addRequestList(DHCPConstants.Options.DHO_STATIC_ROUTES)
            .addRequestList(DHCPConstants.Options.DHO_VENDOR_ENCAPSULATED_OPTIONS)
            .build(TEST_SERVER_MAC)

        val nif = Pcaps.findAllDevs()[4]
        val handle = nif.openLive(
            65536,
            PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
            10
        )
        handle.sendPacket(eth)
    }

    private fun sendRequest() {
        val eth = DHCPUtil.DHCPPacketBuilder()
            .op(DHCPConstants.OP.BOOT_REQUEST)
            .htype(DHCPConstants.HType.ETHER)
            .hlen(DHCPConstants.HLen.ETHER)
            .xid(Random().nextInt())
            // Request 时需要使用 Offer 发过来的 IP
            .ciaddr(FAKE_IP)
            .chaddr(FAKE_MAC)

            .setMessageType(DHCPConstants.Message.DHCP_REQUEST)
            .setHostName("TEST")
            .setFQDN("TEST FQ")
            .addRequestList(DHCPConstants.Options.DHO_SUBNET_MASK)
            .addRequestList(DHCPConstants.Options.DHO_ROUTERS)
            .addRequestList(DHCPConstants.Options.DHO_DOMAIN_NAME_SERVERS)
            .addRequestList(DHCPConstants.Options.DHO_DOMAIN_NAME)
            .addRequestList(DHCPConstants.Options.DHO_PERFORM_MASK_DISCOVERY)
            .addRequestList(DHCPConstants.Options.DHO_STATIC_ROUTES)
            .addRequestList(DHCPConstants.Options.DHO_VENDOR_ENCAPSULATED_OPTIONS)
            .build(TEST_SERVER_MAC)


        val nif = Pcaps.findAllDevs()[4]
        val handle = nif.openLive(
            65536,
            PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
            10
        )
        handle.sendPacket(eth)
    }

}