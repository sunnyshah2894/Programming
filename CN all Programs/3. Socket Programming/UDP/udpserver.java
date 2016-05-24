import java.io.*;
import java.net.*;

class udpserver
{
  public static void main(String args[]) throws Exception
    {
      DatagramSocket serverSocket = new DatagramSocket(9877);
      byte[] receivebuffer = new byte[256];
      byte[] sendbuffer  = new byte[256];
      while(true)
        {
          DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
          serverSocket.receive(recvdpkt);
          InetAddress IP = recvdpkt.getAddress();
          int portno = recvdpkt.getPort();
          String clientdata = new String(recvdpkt.getData());
          System.out.print("\nClient : "+ clientdata);
          System.out.print("\nServer : ");
          BufferedReader serverRead = new BufferedReader(new InputStreamReader(System.in));
          String serverdata = serverRead.readLine();
          sendbuffer = serverdata.getBytes();
          DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP,portno);
          serverSocket.send(sendPacket);
        }
    }
}

/*
Output :

Client : hi
Server : hello

Client : how r u??
Server : fine!!

Process interrupted by user.
*/