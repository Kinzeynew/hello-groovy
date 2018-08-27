// 从 CIDR 计算网络地址,掩码,主机IP范围,主机数等

// 掩码部分 1~30 有效

// 十进制地址 转换为 A.B.C.D 四分法显示
def ipDisplay(ip) {
    D = ip & 255
    C = (ip >> 8) & 255
    B = (ip >> 16) & 255
    A = (ip >> 24) & 255
    A + "." + B + "." + C + "." + D
}

def cidr = '192.168.0.1/27'					// CIDR input
def mask = cidr.split("/")[1].toInteger()	// 掩码十进制部分
def ip = cidr.split("/")[0].split('\\.')	// IP数组部分
def maskStringLeft = "1" * mask 			// 掩码全1部分
def maskString = maskStringLeft + "0" * ( 32 - maskStringLeft.length())  // 补齐32位掩码
def ips = 0L + (ip[0].toInteger() << 24) + (ip[1].toInteger() << 16) + (ip[2].toInteger() << 8) + ip[3].toInteger()  // IP十进制值
def network  = Long.valueOf(maskString, 2) & ips  	// 网络地址
def hosts = (1 << (32 - mask)) - 2					// 主机数量
def broadcast = network + hosts + 1					// 广播地址

println "address:" + ipDisplay(ips)
println "bitmask:" + mask
println "netmask:" + ipDisplay(Long.valueOf(maskString, 2))
println "network:" + ipDisplay(network)
println "hostMin:" + ipDisplay(network + 1)
println "hostMax:" + ipDisplay(broadcast - 1)
println "broadcast:" + ipDisplay(broadcast)
println "hosts:" + hosts




