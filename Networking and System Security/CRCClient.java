/*
 * Author: Sunny Shah
 * Sardar Patel Institute Of Technology
 * T.E. Computer 
 * codechefid: sunnyshah28  
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class CRCClient {

	public CRCClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Socket socket;
		
		
		try{
			socket = new Socket("localhost",9967);
			System.out.println("Connected To the Server!");
		}
		catch(Exception e){
			System.out.println("Could not Connect to The server!");
			return;
		}
		
		
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		
		System.out.println("Enter the CodeWord: ");
		String codeword = br.readLine();
		
		char code[] = codeword.toCharArray();
		char divisor[] = {'1','0','1','1'};
		char act_code[] = new char[code.length + 3];
		char temp_code[] = new char[code.length + 3];
		
		
		for(int i=0;i< code.length;i++)temp_code[i] = code[i];
		for(int i=0;i<divisor.length-1;i++){
			temp_code[code.length+i] = '0';
		}
		
		//System.out.println(new String(temp_code));
		
		//division Procedure
		for(int i=0;i<code.length;i++){
			//System.out.println(new String(temp_code)+ "::"+new String(divisor));
			for(int j=i;j<i+divisor.length;j++){
				
				if( temp_code[j] == divisor[j-i] )temp_code[j]='0';
				else temp_code[j]='1';
				
			}
			
			
		}
		
		for(int i=0;i< code.length;i++)act_code[i] = code[i];
		for(int i=0;i<divisor.length-1;i++){
			act_code[code.length+i] = temp_code[code.length+i];
		}
		
		String codeword_data = new String(act_code);
		//System.out.println(codeword_data);
		
		out.println(codeword_data);
		
		System.out.println("Server's Reply: " + in.readLine());
		socket.close();
		
	}

}
