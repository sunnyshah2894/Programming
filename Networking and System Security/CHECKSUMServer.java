import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class CHECKSUMServer {

	public CHECKSUMServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		
		ServerSocket serversocket = new ServerSocket(9967);
		System.out.println("Server Started!");
		while(true){
			
			Socket socket = serversocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			int n = Integer.parseInt(in.readLine());
			int ar[] = new int[n+1];
			for(int i = 0;i<n+1;i++){
				
				ar[i] = Integer.parseInt(in.readLine());
				//System.out.println(ar[i]);
			}
			
			int sum = 0;
			for(int i=0;i< n+1;i++)sum += (int)ar[i];
			//System.out.println(sum);
			String bin = Integer.toBinaryString(sum);
			String temp = bin;
			
			while(temp.length()>4){
				int sum1 = Integer.parseInt(temp.substring(4, temp.length()),2);
				
				int sum2 = Integer.parseInt(temp.substring(0,4),2);
				sum = sum1 + sum2;
				temp = Integer.toBinaryString(sum);
			}
			System.out.println(sum);
			sum=sum^15;
			//System.out.println(sum);
			
			
			if(sum == 0){
				
				System.out.println("Numbers Were Received Without Error!");
				for(int i =0 ;i<ar.length-1;i++)System.out.print((int)ar[i]+" ");
				out.println("Success!");
				socket.close();
			
				
			}
			else{
				System.out.println("There was a error in receiving the numbers!");
				out.println("There was a error in receiving the numbers!");
				socket.close();
				break;
			}
			
			
		}
		
		serversocket.close();
	}
	
}
