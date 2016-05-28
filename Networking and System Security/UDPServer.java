import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


public class UDPServer {

	public UDPServer() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String [] sdfs)throws IOException{
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			DatagramSocket DSs = new DatagramSocket(9967);
			byte mila[] = new byte[100];
			byte gela[] = new byte[100];
			while(true){
					
				DatagramPacket DPin = new DatagramPacket(mila,mila.length);
				DSs.receive(DPin);
				//System.out.println(Arrays.toString(mila));
				String aaya = new String(DPin.getData());
				System.out.println("From Client:  "+ aaya);
				if(aaya.equalsIgnoreCase("exit"))break;
				InetAddress clientKaAddress = DPin.getAddress();
				int port = DPin.getPort();
				System.out.println("add: "+ clientKaAddress+"  port: "+port);
				String message = "Server has got ur packets!";
				gela = message.getBytes();
				DatagramPacket DPout = new DatagramPacket(gela,gela.length,clientKaAddress,port);
				DSs.send(DPout);
				
			}
			DSs.close();
			
			
	}

}
