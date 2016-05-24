set ns [new Simulator]

set nf [open out.nam w]
$ns namtrace-all $nf

set tf [open out.tr w]
$ns trace-all $tf

proc finish {}
{
	global ns tf nf
	$ns flush-trace
	close $nf
	close $tf
	#exec nam out.nam &
	exit 0
}

$ns color 0 Red
$ns color 1 Blue
$ns color 2 Yellow
$ns color 3 Orange
$ns color 4 Green
$ns color 5 Pink
$ns color 6 Black
$ns color 7 Violet


set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
set n7 [$ns node]
set n8 [$ns node]
set n9 [$ns node]
set n10 [$ns node]
set n11 [$ns node]
set n12 [$ns node]
set n13 [$ns node]
set n14 [$ns node]
set n15 [$ns node]
set n16 [$ns node]
set n17 [$ns node]
set n18 [$ns node]
set n19 [$ns node]
set n20 [$ns node]


$ns duplex-link $n7 $n8 5Mb 2ms DropTail
$ns duplex-link $n8 $n9 5Mb 2ms DropTail
$ns duplex-link $n9 $n10 5Mb 2ms DropTail
$ns duplex-link $n7 $n4 5Mb 2ms DropTail
$ns duplex-link $n4 $n2 5Mb 2ms DropTail
$ns duplex-link $n8 $n5 5Mb 2ms DropTail
$ns duplex-link $n5 $n9 5Mb 2ms DropTail
$ns duplex-link $n2 $n5 5Mb 2ms DropTail
$ns duplex-link $n5 $n3 5Mb 2ms DropTail
$ns duplex-link $n10 $n6 5Mb 2ms DropTail

$ns duplex-link $n6 $n3 5Mb 2ms DropTail
$ns duplex-link $n2 $n1 5Mb 2ms DropTail
$ns duplex-link $n3 $n1 5Mb 2ms DropTail
$ns duplex-link $n1 $n11 5Mb 2ms DropTail
$ns duplex-link $n1 $n12 5Mb 2ms DropTail
$ns duplex-link $n12 $n14 5Mb 2ms DropTail
$ns duplex-link $n11 $n14 5Mb 2ms DropTail
$ns duplex-link $n12 $n15 5Mb 2ms DropTail
$ns duplex-link $n4 $n8 5Mb 2ms DropTail
$ns duplex-link $n15 $n19 5Mb 2ms DropTail

$ns duplex-link $n18 $n19 5Mb 2ms DropTail
$ns duplex-link $n14 $n18 5Mb 2ms DropTail
$ns duplex-link $n14 $n17 5Mb 2ms DropTail
$ns duplex-link $n18 $n20 5Mb 2ms DropTail
$ns duplex-link $n16 $n17 5Mb 2ms DropTail
$ns duplex-link $n17 $n20 5Mb 2ms DropTail
$ns duplex-link $n16 $n13 5Mb 2ms DropTail
$ns duplex-link $n14 $n20 5Mb 2ms DropTail
$ns duplex-link $n17 $n18 5Mb 2ms DropTail
$ns duplex-link $n16 $n20 5Mb 2ms DropTail


#$ns make-lan "$n0 $n1 $n2" 5Mb 2ms LL Queue/DropTail Mac/802_3

set tcp [new Agent/TCP]
$tcp set class_ 0
$ns attach-agent $n12 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n17 $sink
$ns connect $tcp $sink

set tcp0 [new Agent/TCP]
$tcp0 set class_ 1
$ns attach-agent $n10 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n16 $sink0
$ns connect $tcp0 $sink0

set tcp1 [new Agent/TCP]
$tcp1 set class_ 2
$ns attach-agent $n2 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n9 $sink1
$ns connect $tcp1 $sink1

set tcp2 [new Agent/TCP]
$tcp2 set class_ 3
$ns attach-agent $n19 $tcp2
set sink2 [new Agent/TCPSink]
$ns attach-agent $n20 $sink2
$ns connect $tcp2 $sink2

set tcp3 [new Agent/TCP]
$tcp3 set class_ 4
$ns attach-agent $n13 $tcp3
set sink3 [new Agent/TCPSink]
$ns attach-agent $n20 $sink3
$ns connect $tcp3 $sink3


set udp [new Agent/UDP]
$udp set class_ 0
$ns attach-agent $n11 $udp
set null [new Agent/Null]
$ns attach-agent $n18 $null
$ns connect $udp $null

set udp0 [new Agent/UDP]
$udp0 set class_ 1
$ns attach-agent $n7 $udp0
set null0 [new Agent/Null]
$ns attach-agent $n19 $null0
$ns connect $udp0 $null0

set udp1 [new Agent/UDP]
$udp1 set class_ 2
$ns attach-agent $n3 $udp1
set null1 [new Agent/Null]
$ns attach-agent $n8 $null1
$ns connect $udp1 $null1

set udp2 [new Agent/UDP]
$udp2 set class_ 3
$ns attach-agent $n19 $udp2
set null2 [new Agent/Null]
$ns attach-agent $n14 $null2
$ns connect $udp2 $null2

set udp3 [new Agent/UDP]
$udp3 set class_ 4
$ns attach-agent $n7 $udp3
set null3 [new Agent/Null]
$ns attach-agent $n10 $null3
$ns connect $udp3 $null3


set ftp [new Application/FTP]
$ftp attach-agent $tcp
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2
set ftp3 [new Application/FTP]
$ftp3 attach-agent $tcp3


set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1
set cbr2 [new Application/Traffic/CBR]
$cbr2 attach-agent $udp2

set cbr3 [new Application/Traffic/CBR]
$cbr3 attach-agent $udp3


$ns at 0.5 "$cbr start"
$ns at 2.5 "$cbr stop"
$ns at 0.5 "$ftp start"
$ns at 2.5 "$ftp stop"
$ns at 0.5 "$cbr0 start"
$ns at 2.5 "$cbr0 stop"
$ns at 0.5 "$ftp0 start"
$ns at 2.5 "$ftp0 stop"


$ns at 0.5 "$cbr1 start"
$ns at 2.5 "$cbr1 stop"
$ns at 0.5 "$ftp1 start"
$ns at 2.5 "$ftp1 stop"
$ns at 0.5 "$cbr2 start"
$ns at 2.5 "$cbr2 stop"
$ns at 0.5 "$ftp2 start"
$ns at 2.5 "$ftp2 stop"

$ns at 0.5 "$cbr3 start"
$ns at 2.5 "$cbr3 stop"
$ns at 0.5 "$ftp3 start"
$ns at 2.5 "$ftp3 stop"



$ns at 2.5 "$ns detach-agent $n12 $tcp;$ns detach-agent $n17 $sink"
$ns at 2.5 "$ns detach-agent $n10 $tcp0;$ns detach-agent $n16 $sink0"

$ns at 2.5 "$ns detach-agent $n2 $tcp1;$ns detach-agent $n9 $sink1"
$ns at 2.5 "$ns detach-agent $n19 $tcp2;$ns detach-agent $n20 $sink2"

$ns at 2.5 "$ns detach-agent $n13 $tcp3;$ns detach-agent $n20 $sink3"
 

$ns at 3.0 "finish"

$ns run

