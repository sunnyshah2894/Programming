import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


public class HAMMINGServer {

	public HAMMINGServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		
		ServerSocket serversocket = new ServerSocket(9967);
		
		while(true){
			
			Socket socket = serversocket.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			String codeword = in.readLine();
			int ar[] = new int[4];
			int r[] = new int[3];
			for(int i=0;i<4;i++) ar[i] = (int)codeword.charAt(3-i)-48;
			for(int i=0;i<3;i++) r[i] = (int)codeword.charAt(4+i)-48;
			
			System.out.println(Arrays.toString(ar));
			System.out.println(Arrays.toString(r));
			int s0,s1,s2;
			s0 = ( ar[0] + ar[2] + ar[1] + r[0] )%2;
			s1 = ( ar[1] + ar[2] + ar[3] + r[1] )%2;
			s2 = ( ar[1] + ar[0] + ar[3] + r[2] )%2;
			
			if( s0==0 && s1==0 && s2==0 ){
				
				System.out.println("CodeWord Received: "+ codeword);
				out.println("Success");
				System.out.println("Actual Code: "+codeword.substring(0, 4));
				System.out.println("Code received with no Error!");
				socket.close();
				
			}
			else{
				out.println("There was an Error in receiving the code!");
				System.out.println("CodeWord Received: "+ codeword);
				if( s2==0 && s1==1 && s0==1){
					System.out.println("There is a error in a2");
					ar[2] = ar[2]^1;
				}
				else if( s2==1 && s1==0 && s0==1 ){
					System.out.println("There is a error in a0");
					ar[0] = ar[0]^1;
				}
				else if(  s2==1 && s1==1 && s0==0 ){
					System.out.println("There is a error in a3");
					ar[3] = ar[3]^1;
				}
				else{
					System.out.println("There is a error in a1");
					ar[1] = ar[1]^1;
				}
				
				StringBuffer act_code = new StringBuffer();
				act_code.append(""+ar[3]+""+ar[2]+""+ar[1]+""+ar[0]+""+r[2]+""+r[1]+""+r[0]);
				System.out.println("Correct Code is: "+act_code);
				serversocket.close();
			}
			
			
		}
		
		
	}

}
