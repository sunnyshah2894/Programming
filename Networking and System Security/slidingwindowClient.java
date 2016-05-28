import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class slidingwindowClient {

	public slidingwindowClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException, InterruptedException {
	
		Socket socket = new Socket("localhost",9967);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		while(true){	
			String frame = in.readLine();
			if(frame.equalsIgnoreCase("exit"))break;
			int frameno = Integer.parseInt(frame.substring(frame.length()-1,frame.length() ));
			System.out.println("Received frame "+ frameno);
			
			if(frameno == 1)Thread.sleep(2000);
			
			System.out.println("Sending ack for frame "+frameno);
			out.println(""+frameno);
			
		}
		socket.close();
		
	}

}