import java.net.*;
import java.io.*;

public class tcpserver extends Thread
{
   private ServerSocket serverSocket;

   public tcpserver(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to "+ server.getLocalSocketAddress() + "\nGoodbye!");
            server.close();
         }
         catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }
         catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      try
      {
         Thread t = new tcpserver(6066);
         t.start();
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}

/*
Output :
Waiting for client on port 6066...
Just connected to /127.0.0.1:3677
Hello from /127.0.0.1:3677
Waiting for client on port 6066...
Socket timed out!

Process completed.
*/