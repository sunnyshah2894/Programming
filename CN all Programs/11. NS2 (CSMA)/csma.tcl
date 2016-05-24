
set ns [new Simulator]

#Define different colors for data flow
$ns color 0 blue
$ns color 1 green

#Open NAM and trace files
set nf [open out.nam w]
$ns namtrace-all $nf

set tf [open out.tr w]
$ns trace-all $tf

#Define a finish procedure
proc finish {} {
	global ns tf nf
	$ns flush-trace
	close $nf
	close $tf
	exec nam out.nam &
	exit 0
}


#Create five nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]


#Form LAN
$ns make-lan "$n0 $n1 $n2 $n3 $n4" 2Mb 10ms LL Queue/DropTail Mac/802_3

#udp1
set udp1 [new Agent/UDP]
$ns attach-agent $n0 $udp1
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1
set null1 [new Agent/Null]
$ns attach-agent $n2 $null1
$ns connect $udp1 $null1
$udp1 set class_ 1

$ns at 1.0 "$cbr1 start"

#tcp1
set tcp1 [new Agent/TCP]
$ns attach-agent $n3 $tcp1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n4 $sink1
$ns connect $tcp1 $sink1
$tcp1 set class_ 2

$ns at 2.0 "$ftp1 start"

#Decide timeline for the simulator
$ns at 3.0 "$ftp1 stop"

$ns at 4.0 "finish"
$ns run



