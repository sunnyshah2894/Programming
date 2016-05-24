#Aim:Design a network topology with 20 nodes and 30 linksGenerate tcp traffic with FTP protocol between 10 nodes.simulate multicast routing. 

set ns [new Simulator]
$ns multicast
set tf [open out.tr w]
$ns trace-all $tf
set nf [open out.nam w]
$ns namtrace-all $nf

$ns color 0 Blue
$ns color 1 Red

proc finish {} {
            global ns tf nf
            $ns flush-trace
            close $tf
            close $nf
            exec nam out.nam &
            exit 0
}

set n0 [$ns node]
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

$ns duplex-link $n0 $n1 5Mb 2ms DropTail
$ns duplex-link $n1 $n2 5Mb 2ms DropTail
$ns duplex-link $n2 $n3 1.5Mb 10ms DropTail
$ns duplex-link $n3 $n4 5Mb 2ms DropTail
$ns duplex-link $n4 $n5 5Mb 2ms DropTail
$ns duplex-link $n5 $n6 1.5Mb 10ms DropTail
$ns duplex-link $n6 $n7 5Mb 2ms DropTail
$ns duplex-link $n7 $n8 5Mb 2ms DropTail
$ns duplex-link $n8 $n9 1.5Mb 10ms DropTail
$ns duplex-link $n9 $n10 5Mb 2ms DropTail
$ns duplex-link $n10 $n11 5Mb 2ms DropTail
$ns duplex-link $n11 $n12 1.5Mb 10ms DropTail
$ns duplex-link $n12 $n13 5Mb 2ms DropTail
$ns duplex-link $n13 $n14 5Mb 2ms DropTail
$ns duplex-link $n14 $n0 1.5Mb 10ms DropTail
$ns duplex-link $n1 $n4 5Mb 2ms DropTail
$ns duplex-link $n0 $n5 5Mb 2ms DropTail
$ns duplex-link $n14 $n6 1.5Mb 10ms DropTail
$ns duplex-link $n13 $n7 5Mb 2ms DropTail
$ns duplex-link $n12 $n8 5Mb 2ms DropTail
$ns duplex-link $n11 $n9 1.5Mb 10ms DropTail
$ns duplex-link $n14 $n1 5Mb 2ms DropTail
$ns duplex-link $n11 $n7 5Mb 2ms DropTail
$ns duplex-link $n3 $n12 1.5Mb 10ms DropTail
$ns duplex-link $n4 $n6 5Mb 2ms DropTail

$ns mrtproto DM
set group1 [Node allocaddr]
set group2 [Node allocaddr]

#group 1
set tcp0 [new Agent/TCP]
$tcp0 set class_ 0
$ns attach-agent $n11 $tcp0
$tcp0 set dst_addr_ $group1
$tcp0 set dst_port_ 0
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

#group 2
set tcp1 [new Agent/TCP]
$tcp1 set class_ 1
$ns attach-agent $n12 $tcp1
$tcp0 set dst_addr_ $group2
$tcp0 set dst_port_ 0
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1

set sink2 [new Agent/TCPSink]
$ns attach-agent $n2 $sink2

set sink5 [new Agent/TCPSink]
$ns attach-agent $n5 $sink5

set sink6 [new Agent/TCPSink]
$ns attach-agent $n6 $sink6

set sink7 [new Agent/TCPSink]
$ns attach-agent $n7 $sink7

set sink10 [new Agent/TCPSink]
$ns attach-agent $n10 $sink10

set sink13 [new Agent/TCPSink]
$ns attach-agent $n13 $sink13

$ns at 0.1 "$n10 join-group $sink10 $group1"
$ns at 0.2 "$n13 join-group $sink13 $group1"

$ns at 0.3 "$n2 join-group $sink2 $group2"
$ns at 0.25 "$n5 join-group $sink5 $group2"
$ns at 0.15 "$n6 join-group $sink6 $group2"
$ns at 0.4 "$n7 join-group $sink7 $group2"

$ns at 0.5 "$ftp0 start"
$ns at 1.0 "$ftp1 start"
$ns at 2.0 "finish"


$ns run

