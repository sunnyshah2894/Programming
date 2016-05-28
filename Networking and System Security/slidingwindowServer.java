import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class slidingwindowServer implements Runnable{

	
	static int n = 0;
	static int frames[];
	static boolean ack[];
	static Thread cheker;
	static int current = 0;
	static ServerSocket serversocket;
	static BufferedReader in;
	static PrintWriter out;
	static Socket socket;
	static int window=4;
	
	public slidingwindowServer() {

		cheker = new Thread(this,"cheker");
		cheker.start();
		
	}
	
	public static void main(String[] args)throws IOException, InterruptedException {
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		serversocket = new ServerSocket(9967);
		socket = serversocket.accept();
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()) );
		out = new PrintWriter(socket.getOutputStream(),true);
		
		System.out.println("Enter The number Of Frames: ");
		n = Integer.parseInt(br.readLine());
		frames = new int[n];
		ack = new boolean[n];
		System.out.println("Enter The Frames: ");
		for(int i=0;i<n;i++){
			frames[i] = Integer.parseInt(br.readLine());
			ack[i] = false;
		}
		new slidingwindowServer();
		while(true){
			
			for(int i = current;i<window+current;i++){
				
				if(i<n){
					
					if(!ack[i]){
						
						System.out.println("Sending Frame "+i);
						out.println("frame"+ i);
						Thread.sleep(2000);
						
					}
					
					
				}
				
			}
			
			if(current == n){
				out.println("exit");
				break;
			}
			
			Thread.sleep(3000);
			
		}
		
		serversocket.close();
		socket.close();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int temp = -1;
		try{
			while(true){
				
				temp = Integer.parseInt(in.readLine());
				System.out.println("Received Ack for frame "+ temp);
				ack[temp] = true;
				if(temp==current)current++;
				if(current == n)break;
				
			}
			
		}
		catch(Exception e){}
			
	}

}
