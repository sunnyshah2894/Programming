import java.io.*;
import java.net.*;

class udpclient
{
    public static void main(String args[]) throws Exception
    {
      byte[] sendbuffer = new byte[256];
      byte[] receivebuffer = new byte[256];
      BufferedReader clientRead =new BufferedReader(new InputStreamReader(System.in));
      InetAddress IP = InetAddress.getByName("localhost");
      while(true)
      {
      DatagramSocket clientSocket = new DatagramSocket();
      System.out.print("\nClient: ");
      String clientData = clientRead.readLine();
      sendbuffer = clientData.getBytes();
      DatagramPacket sendPacket =
      new DatagramPacket(sendbuffer, sendbuffer.length, IP, 9877);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket =
      new DatagramPacket(receivebuffer, receivebuffer.length);
      clientSocket.receive(receivePacket);
      String serverData = new String(receivePacket.getData());
      System.out.print("\nServer: " + serverData);
      clientSocket.close();
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