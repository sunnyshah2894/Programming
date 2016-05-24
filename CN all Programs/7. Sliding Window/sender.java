import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;

class sender
{
	static int window_size=3;
	static int seq_no=7;
	static Timer[] timers;
	static String[] window;
	static boolean[] received;
	static int ptr=0;
	static DataInputStream dis;
	static DataOutputStream dos;

	public static void main(String[] args) throws Exception
	{
		ServerSocket server=new ServerSocket(6066);
		Scanner sc=new Scanner(System.in);
		sc.useDelimiter("\n");
		Socket client=server.accept();
		System.out.println("Connected to "+client.getLocalAddress());
		InputStream r=client.getInputStream();
		OutputStream w=client.getOutputStream();
		w.flush();

		dis=new DataInputStream(r);
		dos=new DataOutputStream(w);
		window=new String[window_size];
		timers=new Timer[window_size];
		received=new boolean[window_size];
		Thread receive=new Thread(new ReceiveThread());
		Thread send=new Thread(new SendThread());
		send.start();
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

class SendThread implements Runnable,ActionListener
{
	SendThread()
	{
		for(int i=0;i<sender.window_size;i++)
		{
			sender.timers[i]=new Timer(2000,this);
			sender.timers[i].setActionCommand(i+"");
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			int timerno=Integer.parseInt(e.getActionCommand());
			if(sender.timers[timerno]!=null)
			{
				sender.timers[timerno].restart();
				System.out.println("Timer for frame no "+timerno+" is fired ");
				System.out.println("Frame "+timerno+" is resent");
				sender.dos.writeUTF(""+timerno);
			}
		}
		catch(SocketException ex){}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Finished sending");
		}
	}

	public void run()
	{
		Random rand=new Random();
		boolean over=false;
		while(!sender.stop() && !over)
		{
			try
			{
				int t=Math.abs(rand.nextInt());
				if(!sender.received[sender.ptr])
				{
					if(t%3==0)//drop the frame
					{
						System.out.println("Frame "+sender.ptr+" is lost");
					}
					else
					{
						System.out.println("Frame "+sender.ptr+" is sent");
						sender.timers[sender.ptr].start();
						sender.dos.writeUTF(""+sender.ptr);
					}
				}
				sender.ptr++;

				if(sender.ptr==sender.window_size)
				{
					sender.ptr=0;
					Thread.sleep(3000);
				}
				Thread.sleep(1000);
			}
			catch(SocketException e) {}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Finished sending");
				over=true;
			}
		}
	}
}

class ReceiveThread implements Runnable
{
	public void run()
	{
		boolean over=false;
		while(!sender.stop() && !over)
		{
			try
			{
				String rec=sender.dis.readUTF();
				if(rec.startsWith("ack"))
				{
					int ackno=Integer.parseInt(rec.charAt(3)+"");
					System.out.println("Received ack as ack "+ackno);
					sender.received[ackno]=true;
					sender.timers[ackno].stop();
				}

				if(rec.startsWith("nak"))
				{
					int ackno=Integer.parseInt(rec.charAt(3)+"");
					System.out.println("Received nak as nak "+ackno);
					System.out.println("Frame "+ackno+" is resent");
					sender.dos.writeUTF(""+ackno);
					sender.timers[ackno].restart();
				}
				Thread.sleep(1000);
			}
			catch(SocketException e){}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Finished sending");
				over=true;
			}
		}
	}
}