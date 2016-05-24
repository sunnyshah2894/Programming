import java.io.*;
import java.net.*;

class CRCServer
{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	String gx="10101";
	String codeword="";
	CRCServer(){}
	void run()
	{
		try
		{
			providerSocket = new ServerSocket(2005, 10);
			System.out.println("Waiting for connection");
			connection = providerSocket.accept();
			System.out.println("Connection received from " + connection.getInetAddress().getHostName());
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			sendMessage("Connection successful");
			do
			{
				try
				{
					message = (String)in.readObject();
					System.out.println("client>"+message);
					if (message.equals("bye"))
						sendMessage("bye");
					else
					{
						CRC c=new CRC();
						codeword=message;
						boolean error=c.errordetect(codeword,gx);
						String msg="";
						if(!error)
							msg="The Transmitted codeword is Recieved without errors";
						else
							msg="The Transmitted codeword is Recieved with error(s)";
						sendMessage(msg);
					}
				}
				catch(ClassNotFoundException classnot)
				{
					System.err.println("Data received in unknown format");
				}
			}while(!message.equals("bye"));
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally
		{
			try
			{
				in.close();
				out.close();
				providerSocket.close();
			}
			catch(IOException ioException)
			{
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try
		{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		CRCServer server = new CRCServer();
			server.run();
	}
}

class CRC
{
	public static boolean errordetect(String codeword,String gx)
	{
		int i=0,j;
		String temp=codeword;
		String dividend=temp.substring(0,5);
		String rem="";
		while(i<=temp.length()-gx.length())
		{
			for(j=0;j<gx.length();j++)
			{
				if(dividend.charAt(j)!=gx.charAt(j))
					rem=rem+"1";
				else if(!rem.equals("") && dividend.charAt(j)==gx.charAt(j))
					rem=rem+"0";
			}
			while(rem.length()!=gx.length())
			{
				if(i+j<temp.length())
					rem=rem+temp.charAt(i+j);
				else
					break;
				i++;
			}
			dividend=rem;
			rem="";
			if(dividend.length()!=gx.length())
				break;
		}
		while(dividend.length()!=gx.length())
			dividend="0"+dividend;
		boolean error=false;
		for(i=0;i<dividend.length();i++)
		{
			if(dividend.charAt(i)=='1')
			{
				error=true;
				break;
			}
		}
		return error;
	}
}

/*
Output :
Waiting for connection
Connection received from 127.0.0.1
server>Connection successful
client>11001010100011
server>The Transmitted codeword is Recieved without errors
client>bye
server>bye

Process completed.
*/