import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;

class receiver
{
	static int window_size=3;
	static int seq_no=7;
	static String[] window;
	static boolean[] received;
	static int ptr=0;
	static int marker=0;
	static DataInputStream dis;
	static DataOutputStream dos;

	public static void main(String[] args) throws Exception
	{
		Socket client=new Socket("localhost",6066);
		Scanner sc=new Scanner(System.in);
		sc.useDelimiter("\n");
		System.out.println("Connected to "+client.getRemoteSocketAddress());
		InputStream r=client.getInputStream();
		OutputStream w=client.getOutputStream();
		w.flush();

		dis=new DataInputStream(r);
		dos=new DataOutputStream(w);
		window=new String[window_size];
		received=new boolean[window_size];
		Thread receive=new Thread(new ReceiveThread2());
		receive.start();
	}

	public static boolean stop()
	{
		for(int i=0;i<window_size;i++)
		{
			if(received[i]==false)
			return false;
		}
		return true;
	}
}
class ReceiveThread2 implements Runnable
{
	public void run()
	{
		Random rand=new Random();
		while(!receiver.stop())
		{
			try
			{
				String rec=receiver.dis.readUTF();
				int seqno=Integer.parseInt(rec);
				System.out.println("Received frame no "+seqno);
				if(!receiver.received[receiver.ptr])
				System.out.println("Expected frame no "+receiver.ptr);
				receiver.received[seqno]=true;

				if(seqno==receiver.ptr)
				{
					System.out.println("Sending acknowledgement as ack"+seqno);
					receiver.dos.writeUTF("ack"+seqno);
					receiver.ptr++;
					if(receiver.ptr==receiver.window_size)
					receiver.ptr=0;
				}
				else
				{
					System.out.println("Sending acknowledgement as ack"+seqno);
					receiver.dos.writeUTF("ack"+seqno);
					Thread.sleep(1000);
					for(int i=receiver.ptr;i<=seqno;i++)
					{
						if(!receiver.received[i])
						{
							System.out.println("Sending negative acknowledgement as nak"+i);
							receiver.dos.writeUTF("nak"+i);
							Thread.sleep(500);
						}
					}
					receiver.ptr=seqno+1;
					if(receiver.ptr==receiver.window_size)
					{
						for(int i=0;i<receiver.window_size;i++)
						{
							receiver.ptr=i;
							if(!receiver.received[i])
							break;
						}
					}
				}
				Thread.sleep(1000);
			}
			catch(SocketException e) { }
			catch(Exception e)
			{
			e.printStackTrace();
			}
		}
	}
}