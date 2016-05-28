import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class TCPClient {

	public TCPClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String []  wedw)throws IOException{
		Socket socket = new Socket("localhost",9967);
		while(true){
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter outToServer = new PrintWriter(socket.getOutputStream(),true);
			System.out.println("Enter The Message: ");
			String message = br.readLine();
			outToServer.println(message);
			String serverack = inFromServer.readLine();
			System.out.println(serverack);
			if( serverack.equalsIgnoreCase("exit"))break;
		}
		socket.close();
	}
}
