#Aim:Design a network topology with 15 nodes and 25 links.Generate tcp traffic with FTP protocol between 10 nodes.simulate unicast routing, multicast routing and hierarchical routing. 


set ns [new Simulator]
set tf [open out.tr w]
$ns trace-all $tf
set nf [open out.nam w]
$ns namtrace-all $nf

$ns color 0 blue
$ns color 1 Green
$ns color 2 red
$ns color 3 Yellow

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

#n10 to n13
set tcp0 [new Agent/TCP]
$ns attach-agent $n10 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n13 $sink0
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ns connect $tcp0 $sink0
$tcp0 set class_ 0
$ns at 0.5 "$ftp0 start"
$ns at 2.0 "$ns detach-agent $n10 $tcp0 ; $ns detach-agent $n13 $sink0"

#n0 to n5
set tcp1 [new Agent/TCP]
$ns attach-agent $n0 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n5 $sink1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
$ns connect $tcp1 $sink1
$tcp1 set class_ 1
$ns at 0.75 "$ftp1 start"
$ns at 2.3 "$ns detach-agent $n0 $tcp1 ; $ns detach-agent $n5 $sink1"

#n2 to n12
set tcp2 [new Agent/TCP]
$ns attach-agent $n2 $tcp2
set sink2 [new Agent/TCPSink]
$ns attach-agent $n12 $sink2
set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2
$ns connect $tcp2 $sink2
$tcp2 set class_ 2
$ns at 1.0 "$ftp2 start"
$ns at 1.5 "$ns detach-agent $n2 $tcp2 ; $ns detach-agent $n12 $sink2"

#n14 to n4
set tcp3 [new Agent/TCP]
$ns attach-agent $n14 $tcp3
set sink3 [new Agent/TCPSink]
$ns attach-agent $n4 $sink3
set ftp3 [new Application/FTP]
$ftp3 attach-agent $tcp3
$ns connect $tcp3 $sink3
$tcp3 set class_ 3
$ns at 0.3 "$ftp3 start"
$ns at 1.75 "$ns detach-agent $n14 $tcp3 ; $ns detach-agent $n4 $sink3"

$ns cost $n0 $n1 3
$ns cost $n1 $n2 3
$ns cost $n2 $n3 3
$ns cost $n3 $n4 3
$ns cost $n4 $n5 3
$ns cost $n5 $n6 3
$ns cost $n6 $n7 3
$ns cost $n7 $n8 3
$ns cost $n8 $n9 3
$ns cost $n9 $n10 3
$ns cost $n10 $n11 3
$ns cost $n11 $n12 3
$ns cost $n12 $n13 3
$ns cost $n13 $n14 3
$ns cost $n14 $n0 3


$ns cost $n1 $n4 10
$ns cost $n0 $n5 10
$ns cost $n14 $n6 10
$ns cost $n13 $n7 10
$ns cost $n12 $n8 10
$ns cost $n11 $n9 10

$ns cost $n14 $n1 5
$ns cost $n12 $n3 5
$ns cost $n11 $n7 5
$ns cost $n4 $n6 5

$ns rtproto Static 

$ns at 3.0 "finish"
proc finish {} {
            global ns tf nf
            $ns flush-trace
            close $tf
            close $nf
            exec nam out.nam &
            exit 0
}
# Finally, start the simulation.
$ns run

