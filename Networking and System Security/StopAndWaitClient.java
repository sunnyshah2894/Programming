import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class StopAndWaitClient {

	public StopAndWaitClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		
		Scanner sc = new Scanner(System.in);
		Socket socket;
		
		try{
			socket = new Socket("localhost",9967);
		}
		catch(Exception e){
			System.out.println("Cannot connect to the server!");
			return;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
		System.out.println("Enter The no of Frames to send: ");
		int n = sc.nextInt();
		int ar[] = new int[n];
		System.out.println("Enter the Data bytes: ");
		for(int i =0  ;i< n;i++)ar[i] = sc.nextInt();
		
		
		
		
		for(int i=0;i<n;i++){
		
			long sendTime = System.currentTimeMillis();
			long currTime = System.currentTimeMillis();
			boolean sent = false;
			int count = 0;
			while(!sent){
				
				sendTime = System.currentTimeMillis();
				System.out.println("Sending Frame: "+i+" i.e. "+ar[i]);
				out.println(""+i);
				out.println(""+ar[i]);
				String ack = in.readLine();				
				if( ack.equalsIgnoreCase(""+i)){
					System.out.println("Acknowledgement received!");
					sent = true;
					break;
				}
				else{
					System.out.println("Acknowledgement not correct! Sending the message again!");
					
					sendTime = System.currentTimeMillis();
					currTime = System.currentTimeMillis();
				}
				
			}
			
			
		}
		socket.close();
		
		
	}

}
