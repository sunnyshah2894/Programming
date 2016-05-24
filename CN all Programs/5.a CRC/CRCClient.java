import java.io.*;
import java.net.*;

class CRCClient
{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	String codeword="";
 	String dataword="";
	String gx="";
	CRCClient(){}
	void run()
	{
		try
		{
			requestSocket = new Socket("localhost", 2005);
			System.out.println("Connected to localhost in port 2004");
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			int i=0;
			do{
				try
				{
					message = (String)in.readObject();
					System.out.println("server>" + message);
					if(i==0)
						sendMessage(codeword);
					else
					{
						sendMessage("bye");
						message="bye";
					}
				}
				catch(ClassNotFoundException classNot)
				{
					System.err.println("data received in unknown format");
				}
				finally
				{
					i++;
				}
			}while(!message.equals("bye"));
		}
		catch(UnknownHostException unknownHost)
		{
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
		finally
		{
			try
			{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException)
			{
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[]) throws IOException
	{
		CRC c=new CRC();
		CRCClient client = new CRCClient();
		BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the codeword : ");
    	client.dataword = inFromUser.readLine();
    	System.out.print("Enter the g(x) : ");
    	client.gx = inFromUser.readLine();
		String chksum=c.checksum(client.dataword,client.gx);
		client.codeword=client.dataword+chksum;
		client.run();
	}
}
class CRC
{
	public static String checksum(String dataword,String gx)
	{
		int i,j;
		String zeroes="";
		for(i=0;i<gx.length();i++)
			zeroes=zeroes+"0";
		String temp=dataword+zeroes;
		String dividend=temp.substring(0,5);
		String rem="";
		i=0;
		while(i<=dataword.length())
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
		return dividend;
	}
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
Enter the codeword : 110010101
Enter the g(x) : 10101
Connected to localhost in port 2004
server>Connection successful
client>11001010100011
server>The Transmitted codeword is Recieved without errors
client>bye

Process completed.
*/