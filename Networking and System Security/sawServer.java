import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class sawServer implements Runnable {

	
	static int n = 0;
	static int frames[];
	static Thread cheker;
	static int current = 0;
	static ServerSocket serversocket;
	static BufferedReader in;
	static PrintWriter out;
	static Socket socket;
	static boolean aayakya;
	static int kaunaya;
	
	public sawServer() {
		// TODO Auto-generated constructor stub
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
		System.out.println("Enter The Frames: ");
		for(int i=0;i<n;i++){
			frames[i] = Integer.parseInt(br.readLine());
		}
		new sawServer();
		while(true){
			
			System.out.println("Sending Frame "+ current);
			out.println("Frame"+ current);
			aayakya = false;
			kaunaya = -1;
			Thread.sleep(2000);
			
			if(aayakya){
				
				if(current==kaunaya){
					
					current++;
					
				}
				
				
			}
			else System.out.println("TimeOut for frame "+ current);
			if(current==n){
				out.println("exit");
				break;
			}
			Thread.sleep(5000);
			
		}
		
		serversocket.close();
		socket.close();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			int temp= -1;
		
			while(true){
				//System.out.println("ra");
				temp = Integer.parseInt(in.readLine());
				kaunaya = temp;
				System.out.println("Received Ack for frame "+ temp);
				aayakya = true;
			}
		}
		catch(Exception e){}
		
		
	}
	
	

}
