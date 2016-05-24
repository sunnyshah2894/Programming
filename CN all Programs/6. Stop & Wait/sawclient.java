import java.io.*;
import java.net.*;
import java.util.*;

public class sawclient
{
    String data="";

  	public static void main(String args[])
  	{
        int serverPort = 6666;
        String address = "127.0.0.1";

        try
        {
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Connecting to Server at " + address + " and port " + serverPort);

            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("Connected to SERVER!");

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            while(true)
           	{
            int[] x = getData();

            for(int i=0;i<x.length;i++)
            	{
                long sendTime = System.currentTimeMillis();

                while((System.currentTimeMillis() - sendTime)!=5)
                {

                    System.out.println("\nSending : " + x[i]);
                    out.write(x[i]);
                    out.flush();

                    System.out.println("Waiting for ACK...");
                    int rec = in.read();

                    if(rec==x[i])
                    {
                        System.out.println("ACK for " + x[i] + " received.");
                        break;
                    }
                    else
                    {
                    	System.out.println("ACK for NOT received. Send msg again...");
                    	sendTime = System.currentTimeMillis();
                    }
                }
               }
            }
        }
       	catch(Exception e)
       	{
            e.printStackTrace();
        }
 	}

    private static int[] getData()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter Data seperated by spaces : ");
        String x = in.nextLine();
        x = x.trim();
        String[] s = x.split(" ");

        int[] num = new int[s.length];

        for(int i=0;i<num.length;i++)
        {
            num[i] = Integer.parseInt(s[i]);
        }
        return(num);
    }

}

/*
Output :
Connecting to Server at 127.0.0.1 and port 6666
Connected to SERVER!

Enter Data seperated by spaces : 1 0 2 5 2

Sending : 1
Waiting for ACK...
ACK for 1 received.

Sending : 0
Waiting for ACK...
ACK for 0 received.

Sending : 2
Waiting for ACK...
ACK for NOT received. Send msg again...

Sending : 2
Waiting for ACK...
ACK for 2 received.

Sending : 5
Waiting for ACK...
ACK for 5 received.

Sending : 2
Waiting for ACK...
ACK for NOT received. Send msg again...

Sending : 2
Waiting for ACK...
ACK for 2 received.

Enter Data seperated by spaces :
*/