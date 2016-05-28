import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class CHECKSUMClient {

	public CHECKSUMClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		
		Scanner sc = new Scanner(System.in);
		Socket socket;
		try{
			socket = new Socket("localhost",9967);
		}
		catch(Exception e){
			System.out.println("Cannot connect to the Server!");
			return;
		}
		System.out.println("Enter The no. of numbers to be sent: ");
		int n = sc.nextInt();
		int ar[] = new int[n];
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		System.out.println("Enter the array Elements: ");
		for(int i=0;i<n;i++)ar[i] = sc.nextInt();
		
		int sum = 0;
		for(int i=0;i< n;i++)sum += (int)ar[i];
		//System.out.println(sum);
		String bin = Integer.toBinaryString(sum);
		String temp = bin;
		
		//System.out.println(temp);
		while(temp.length()>4){
			int sum1 = Integer.parseInt(temp.substring(4, temp.length()),2);
			
			int sum2 = Integer.parseInt(temp.substring(0,4),2);
			//System.out.println("sum2 "+sum2);
			sum = sum1 + sum2;
			temp = Integer.toBinaryString(sum);
			//System.out.println(temp);
		}
		System.out.println(sum);
		sum=sum^15;
		//System.out.println(sum);
		
		out.println(""+n);
		for(int i=0;i<n;i++)out.println(""+ar[i]);
		out.println("" + sum);
		System.out.println("Server's Reply: " + in.readLine());
		socket.close();
		
		
	}
	
}
