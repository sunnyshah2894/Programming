import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class TCPServer {

	public TCPServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String []jsdjf)throws IOException{
		
			ServerSocket serversocket = new ServerSocket(9967);
			System.out.println("Server has Started!");
			int i = 0;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Socket clientKaSocket = serversocket.accept();
			System.out.println("Client Accepted");
			while(true){
				
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientKaSocket.getInputStream()));
				PrintWriter outToClient = new PrintWriter(clientKaSocket.getOutputStream(),true);
				String fromClient = inFromClient.readLine();
				System.out.println("message from client: " + fromClient);
				System.out.println("Send a message: ");
				fromClient = br.readLine();
				outToClient.println("Server says : " + fromClient);
				if( fromClient.equalsIgnoreCase("exit"))break;
			}
			clientKaSocket.close();
			serversocket.close();
		
	}

}
