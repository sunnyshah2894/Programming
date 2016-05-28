import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;


public class HAMMINGClient {

	public HAMMINGClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		
		System.out.println("Enter The CodeWord: ");
		String codeword = br.readLine();
		System.out.println(codeword);
		int ar[] = new int[4];
		for(int i=0;i<4;i++)ar[i] = (int)codeword.charAt(3-i)-48;
		
		int r0,r1,r2;
		System.out.println(Arrays.toString(ar));
		System.out.println((ar[0] + ar[2] + ar[1]));
		r0 = ( ar[0] + ar[2] + ar[1] )%2;
		r1 = ( ar[1] + ar[2] + ar[3] )%2;
		r2 = ( ar[1] + ar[0] + ar[3] )%2;
		System.out.println(r0+" "+r1+" "+r2);
		StringBuffer message = new StringBuffer(codeword);
		message.append(""+r2+""+r1+""+r0);
		
		out.println(message);
		System.out.println("Server's reply: "+ in.readLine());
		
		socket.close();
		
		
	}

}
