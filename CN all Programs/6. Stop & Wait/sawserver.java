import java.io.*;
import java.net.*;
import java.util.*;

public class sawserver
{
    public static void main(String args[])
    {
        int port = 6666;

        try
        {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("SERVER STARTED.");
            System.out.println("Waiting for a client...");

            Socket s = ss.accept();

            System.out.println("Connected to Client...");
            System.out.println();

            InputStream sin = s.getInputStream();
            OutputStream sout = s.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            System.out.println("Receiving Data...\n\n");
            Random r = new Random();

            while(true)
            {
                int random = r.nextInt(2);

                System.out.println("Waiting For message...");

                int x = in.read();
                System.out.println("Received : " + x);

				System.out.println("Sending ACK...\n");

                if(random==1)
                {
                    out.write(x);
                    out.flush();
                }
                else
                {
                    random = r.nextInt(2);
                    out.write(-1);
                    out.flush();
                }
            }

        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
    }
}

/*
Output :
SERVER STARTED.
Waiting for a client...
Connected to Client...

Receiving Data...


Waiting For message...
Received : 1
Sending ACK...

Waiting For message...
Received : 0
Sending ACK...

Waiting For message...
Received : 2
Sending ACK...

Waiting For message...
Received : 2
Sending ACK...

Waiting For message...
Received : 5
Sending ACK...

Waiting For message...
Received : 2
Sending ACK...

Waiting For message...
Received : 2
Sending ACK...

Waiting For message...
*/