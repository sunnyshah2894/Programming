import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class UDPClient {

	public UDPClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket DS = new DatagramSocket();
		byte mila[] = new byte[100];
		byte gela[] = new byte[100];
		InetAddress serverip = InetAddress.getByName("localhost");
		System.out.println("enter the message: ");
		String message = br.readLine();
		gela = message.getBytes();
		DatagramPacket DPout = new DatagramPacket(gela,gela.length,serverip,9967);
		DS.send(DPout);
		DatagramPacket DPin = new DatagramPacket(mila, mila.length);
		DS.receive(DPin);
		System.out.println(new String(DPin.getData()));
		DS.close();
	}
	
}
