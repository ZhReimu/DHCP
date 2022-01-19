@file:Suppress("UNUSED")

package com.mrx.dhcp4j

import org.pcap4j.util.MacAddress
import java.net.InetAddress

class DHCPConstants private constructor() {

    private interface Constants {
        val value: Byte
    }

    enum class OP : Constants {
        BOOT_REQUEST {
            override val value: Byte = 1
        },
        BOOT_REPLY {
            override val value: Byte = 2
        }
    }

    enum class HType : Constants {
        ETHER {
            override val value: Byte = 1
        },
        IEEE802 {
            override val value: Byte = 6
        },
        FDDI {
            override val value: Byte = 8
        },
        IEEE1394 {
            override val value: Byte = 24
        }
    }

    enum class HLen : Constants {
        ETHER {
            override val value: Byte = 6
        }
    }

    enum class Message : Constants {
        DHCP_DISCOVER {
            override val value: Byte = 1
        },
        DHCP_OFFER {
            override val value: Byte = 2
        },
        DHCP_REQUEST {
            override val value: Byte = 3
        },
        DHCP_DECLINE {
            override val value: Byte = 4
        },
        DHCP_ACK {
            override val value: Byte = 5
        },
        DHCP_NAK {
            override val value: Byte = 6
        },
        DHCP_RELEASE {
            override val value: Byte = 7
        },
        DHCP_INFORM {
            override val value: Byte = 8
        },
        DHCP_FORCE_RENEW {
            override val value: Byte = 9
        },
        DHCP_LEASE_QUERY {
            override val value: Byte = 10
        },// RFC 4388
        DHCP_LEASE_UNASSIGNED {
            override val value: Byte = 11
        }, // RFC 4388
        DHCP_LEASE_UNKNOWN {
            override val value: Byte = 12
        }, // RFC 4388
        DHCP_LEASE_ACTIVE {
            override val value: Byte = 13
        }// RFC 4388
    }

    enum class Options : Constants {
        DHO_PAD {
            override val value: Byte = 0
        },
        DHO_SUBNET_MASK {
            override val value: Byte = 1
        },
        DHO_TIME_OFFSET {
            override val value: Byte = 2
        },
        DHO_ROUTERS {
            override val value: Byte = 3
        },
        DHO_TIME_SERVERS {
            override val value: Byte = 4
        },
        DHO_NAME_SERVERS {
            override val value: Byte = 5
        },
        DHO_DOMAIN_NAME_SERVERS {
            override val value: Byte = 6
        },
        DHO_LOG_SERVERS {
            override val value: Byte = 7
        },
        DHO_COOKIE_SERVERS {
            override val value: Byte = 8
        },
        DHO_LPR_SERVERS {
            override val value: Byte = 9
        },
        DHO_IMPRESS_SERVERS {
            override val value: Byte = 10
        },
        DHO_RESOURCE_LOCATION_SERVERS {
            override val value: Byte = 11
        },
        DHO_HOST_NAME {
            override val value: Byte = 12
        },
        DHO_BOOT_SIZE {
            override val value: Byte = 13
        },
        DHO_MERIT_DUMP {
            override val value: Byte = 14
        },
        DHO_DOMAIN_NAME {
            override val value: Byte = 15
        },
        DHO_SWAP_SERVER {
            override val value: Byte = 16
        },
        DHO_ROOT_PATH {
            override val value: Byte = 17
        },
        DHO_EXTENSIONS_PATH {
            override val value: Byte = 18
        },
        DHO_IP_FORWARDING {
            override val value: Byte = 19
        },
        DHO_NON_LOCAL_SOURCE_ROUTING {
            override val value: Byte = 20
        },
        DHO_POLICY_FILTER {
            override val value: Byte = 21
        },
        DHO_MAX_DGRAM_REASSEMBLY {
            override val value: Byte = 22
        },
        DHO_DEFAULT_IP_TTL {
            override val value: Byte = 23
        },
        DHO_PATH_MTU_AGING_TIMEOUT {
            override val value: Byte = 24
        },
        DHO_PATH_MTU_PLATEAU_TABLE {
            override val value: Byte = 25
        },
        DHO_INTERFACE_MTU {
            override val value: Byte = 26
        },
        DHO_ALL_SUBNETS_LOCAL {
            override val value: Byte = 27
        },
        DHO_BROADCAST_ADDRESS {
            override val value: Byte = 28
        },
        DHO_PERFORM_MASK_DISCOVERY {
            override val value: Byte = 29
        },
        DHO_MASK_SUPPLIER {
            override val value: Byte = 30
        },
        DHO_ROUTER_DISCOVERY {
            override val value: Byte = 31
        },
        DHO_ROUTER_SOLICITATION_ADDRESS {
            override val value: Byte = 32
        },
        DHO_STATIC_ROUTES {
            override val value: Byte = 33
        },
        DHO_TRAILER_ENCAPSULATION {
            override val value: Byte = 34
        },
        DHO_ARP_CACHE_TIMEOUT {
            override val value: Byte = 35
        },
        DHO_IEEE802_3_ENCAPSULATION {
            override val value: Byte = 36
        },
        DHO_DEFAULT_TCP_TTL {
            override val value: Byte = 37
        },
        DHO_TCP_KEEPALIVE_INTERVAL {
            override val value: Byte = 38
        },
        DHO_TCP_KEEPALIVE_GARBAGE {
            override val value: Byte = 39
        },
        DHO_NIS_SERVERS {
            override val value: Byte = 41
        },
        DHO_NTP_SERVERS {
            override val value: Byte = 42
        },
        DHO_VENDOR_ENCAPSULATED_OPTIONS {
            override val value: Byte = 43
        },
        DHO_NETBIOS_NAME_SERVERS {
            override val value: Byte = 44
        },
        DHO_NETBIOS_DD_SERVER {
            override val value: Byte = 45
        },
        DHO_NETBIOS_NODE_TYPE {
            override val value: Byte = 46
        },
        DHO_NETBIOS_SCOPE {
            override val value: Byte = 47
        },
        DHO_FONT_SERVERS {
            override val value: Byte = 48
        },
        DHO_X_DISPLAY_MANAGER {
            override val value: Byte = 49
        },
        DHO_DHCP_REQUESTED_ADDRESS {
            override val value: Byte = 50
        },
        DHO_DHCP_LEASE_TIME {
            override val value: Byte = 51
        },
        DHO_DHCP_OPTION_OVERLOAD {
            override val value: Byte = 52
        },
        DHO_DHCP_MESSAGE_TYPE {
            override val value: Byte = 53
        },
        DHO_DHCP_SERVER_IDENTIFIER {
            override val value: Byte = 54
        },
        DHO_DHCP_PARAMETER_REQUEST_LIST {
            override val value: Byte = 55
        },
        DHO_DHCP_MESSAGE {
            override val value: Byte = 56
        },
        DHO_DHCP_MAX_MESSAGE_SIZE {
            override val value: Byte = 57
        },
        DHO_DHCP_RENEWAL_TIME {
            override val value: Byte = 58
        },
        DHO_DHCP_REBINDING_TIME {
            override val value: Byte = 59
        },
        DHO_VENDOR_CLASS_IDENTIFIER {
            override val value: Byte = 60
        },
        DHO_DHCP_CLIENT_IDENTIFIER {
            override val value: Byte = 61
        },
        DHO_NWIP_DOMAIN_NAME {
            override val value: Byte = 62
        },// rfc 2242
        DHO_NWIP_SUBOPTIONS {
            override val value: Byte = 63
        },// rfc 2242
        DHO_NISPLUS_DOMAIN {
            override val value: Byte = 64
        },
        DHO_NISPLUS_SERVER {
            override val value: Byte = 65
        },
        DHO_TFTP_SERVER {
            override val value: Byte = 66
        },
        DHO_BOOTFILE {
            override val value: Byte = 67
        },
        DHO_MOBILE_IP_HOME_AGENT {
            override val value: Byte = 68
        },
        DHO_SMTP_SERVER {
            override val value: Byte = 69
        },
        DHO_POP3_SERVER {
            override val value: Byte = 70
        },
        DHO_NNTP_SERVER {
            override val value: Byte = 71
        },
        DHO_WWW_SERVER {
            override val value: Byte = 72
        },
        DHO_FINGER_SERVER {
            override val value: Byte = 73
        },
        DHO_IRC_SERVER {
            override val value: Byte = 74
        },
        DHO_STREETTALK_SERVER {
            override val value: Byte = 75
        },
        DHO_STDA_SERVER {
            override val value: Byte = 76
        },
        DHO_USER_CLASS {
            override val value: Byte = 77
        }, // rfc 3004
        DHO_FQDN {
            override val value: Byte = 81
        },
        DHO_DHCP_AGENT_OPTIONS {
            override val value: Byte = 82
        }, // rfc 3046
        DHO_NDS_SERVERS {
            override val value: Byte = 85
        }, // rfc 2241
        DHO_NDS_TREE_NAME {
            override val value: Byte = 86
        },// rfc 2241
        DHO_NDS_CONTEXT {
            override val value: Byte = 87
        }, // rfc 2241
        DHO_CLIENT_LAST_TRANSACTION_TIME {
            override val value: Byte = 91
        }, // rfc 4388
        DHO_ASSOCIATED_IP {
            override val value: Byte = 92
        },// rfc 4388
        DHO_USER_AUTHENTICATION_PROTOCOL {
            override val value: Byte = 98
        },
        DHO_AUTO_CONFIGURE {
            override val value: Byte = 116
        },
        DHO_NAME_SERVICE_SEARCH {
            override val value: Byte = 117
        }, // rfc 2937
        DHO_SUBNET_SELECTION {
            override val value: Byte = 118
        },// rfc 3011
        DHO_DOMAIN_SEARCH {
            override val value: Byte = 119
        }, // rfc 3397
        DHO_CLASSLESS_ROUTE {
            override val value: Byte = 121
        },// rfc 3442
        DHO_END {
            override val value: Byte = -1
        }
    }

    companion object {
        val ANY_ADDRESS: InetAddress = InetAddress.getByName("0.0.0.0")
        val BROAD_CAST: InetAddress = InetAddress.getByName("255.255.255.255")
        val SAMPLE_MAC: MacAddress = MacAddress.getByName("90-09-90-97-91-29")
    }
}