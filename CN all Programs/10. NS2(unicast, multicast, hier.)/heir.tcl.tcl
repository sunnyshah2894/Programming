# To simulate hierarchichal routing

# set address format as hierarchical
set ns [new Simulator]
$ns set-address-format hierarchical

set nf [open out.nam w]
$ns namtrace-all $nf
set tf [open out.tr w]
$ns trace-all $tf

proc finish {} {
            global ns tf nf
            $ns flush-trace
            close $tf
            close $nf
            exec nam out.nam &
            exit 0
}

$ns color 1 Blue
$ns color 2 Red

# define number of domains, clusters and nodes
AddrParams set domain_num_ 2
lappend cluster_num 2 1
AddrParams set cluster_num_ $cluster_num
lappend nodes_num 6 6 3 
AddrParams set nodes_num_ $nodes_num

# set hierarhcial node addresses
set naddr {0.0.0 0.0.1 0.0.2 0.0.3 0.0.4 0.0.5 0.1.5 1.0.2 0.1.0 0.1.1 0.1.2 0.1.3 0.1.4 1.0.0 1.0.1}

# create nodes with appropriate address
for {set i 0} {$i < 15} {incr i} {
	set n$i [$ns node [lindex $naddr $i]]
}

# create links
$ns duplex-link $n0 $n1 0.5Mb 2ms DropTail
$ns duplex-link $n1 $n2 0.5Mb 2ms DropTail
$ns duplex-link $n2 $n4 0.5Mb 2ms DropTail
$ns duplex-link $n3 $n4 0.5Mb 2ms DropTail
$ns duplex-link $n0 $n3 0.5Mb 2ms DropTail
$ns duplex-link $n4 $n5 0.5Mb 2ms DropTail

$ns duplex-link $n6 $n12 0.5Mb 2ms DropTail
$ns duplex-link $n8 $n9 0.5Mb 2ms DropTail
$ns duplex-link $n9 $n10 0.5Mb 2ms DropTail
$ns duplex-link $n10 $n11 0.5Mb 2ms DropTail
$ns duplex-link $n11 $n12 0.5Mb 2ms DropTail
$ns duplex-link $n12 $n8 0.5Mb 2ms DropTail

$ns duplex-link $n7 $n13 0.5Mb 2ms DropTail
$ns duplex-link $n7 $n14 0.5Mb 2ms DropTail
$ns duplex-link $n13 $n14 0.5Mb 2ms DropTail

$ns duplex-link $n5 $n6 0.5Mb 2ms DropTail
$ns duplex-link $n6 $n7 0.5Mb 2ms DropTail
$ns duplex-link $n7 $n5 0.5Mb 2ms DropTail

$ns duplex-link $n1 $n3 0.5Mb 2ms DropTail
$ns duplex-link $n2 $n3 0.5Mb 2ms DropTail
$ns duplex-link $n8 $n10 0.5Mb 2ms DropTail
$ns duplex-link $n10 $n12 0.5Mb 2ms DropTail
$ns duplex-link $n0 $n4 0.5Mb 2ms DropTail
$ns duplex-link $n0 $n2 0.5Mb 2ms DropTail

#tcp1
set tcp1 [new Agent/TCP]
$ns attach-agent $n0 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n10 $sink1
$ns connect $tcp1 $sink1
$tcp1 set class_ 1

set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
$ns at 0.2 "$ftp1 start"

#tcp2
set tcp2 [new Agent/TCP]
$ns attach-agent $n9 $tcp2
set sink2 [new Agent/TCPSink]
$ns attach-agent $n14 $sink2
$ns connect $tcp2 $sink2
$tcp2 set class_ 2

set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2
$ns at 0.4 "$ftp2 start"

#tcp3
set tcp3 [new Agent/TCP]
$ns attach-agent $n1 $tcp3
set sink3 [new Agent/TCPSink]
$ns attach-agent $n3 $sink3
$ns connect $tcp3 $sink3
$tcp3 set class_ 3

set ftp3 [new Application/FTP]
$ftp3 attach-agent $tcp3
$ns at 0.5 "$ftp3 start"

$ns at 1.0 "$ftp1 stop"
$ns at 0.9 "$ftp2 stop"
$ns at 0.8 "$ftp3 stop"
$ns at 1.0 "$ns detach-agent $n0 $tcp1 ; $ns detach-agent $n10 $sink1"
$ns at 1.0 "$ns detach-agent $n9 $tcp2 ; $ns detach-agent $n14 $sink2"
$ns at 1.0 "$ns detach-agent $n1 $tcp3 ; $ns detach-agent $n3 $sink3"
$ns at 1.2 "finish"

$ns run







