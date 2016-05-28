import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class StopAndWaitServer {

	public StopAndWaitServer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)throws IOException {
		
		ServerSocket serversocket = new ServerSocket(9967);
		
		
			
			Socket socket = serversocket.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			System.out.println("connected");
			while(true){
				
				System.out.println("andr");
				String framefromclient = in.readLine();
				System.out.println(framefromclient);
				
				String framedata = in.readLine();
				if(framedata.equals(""+0))break;
				System.out.println(framedata);
				Random r = new Random();
				int no = r.nextInt(2);
				System.out.println("Received Frame: "+ framefromclient+ " i.e. "+framedata);
				System.out.println("Sending acknowledment...");
				if(no==1){
					
					out.println(framefromclient);
					System.out.println(framefromclient);
					
				}
				else{
					out.println("-1");
					System.out.println("wrg");
				}
				
			}
			socket.close();
			serversocket.close();
			
			
		
		
		
	}
	
}
