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
import java.net.ServerSocket;
import java.net.Socket;


public class CRCServer {

	public CRCServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)throws IOException {
		
		ServerSocket serversocket = new ServerSocket(9967);
		System.out.println("Server Started!");
		
		
		while(true){
			
			Socket client = serversocket.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			
			
			String codeword = in.readLine();
			
			char code[] = codeword.toCharArray();
			char divisor[] = {'1','0','1','1'};
			char temp_code[] = new char[code.length];
			char act_code[] = new char[code.length-3];
			
			for(int i=0;i< code.length-divisor.length+1;i++)act_code[i] = code[i];
			
			for(int i=0;i< code.length;i++)temp_code[i] = code[i];
			
			//System.out.println(new String(temp_code));
			for(int i=0;i<code.length-divisor.length+1;i++){
				//System.out.println(new String(temp_code)+ "::"+new String(divisor));
				for(int j=i;j<i+divisor.length;j++){
					
					if( temp_code[j] == divisor[j-i] )temp_code[j]='0';
					else temp_code[j]='1';
					
				}
				
				
			}
			//System.out.println(new String(temp_code));
			boolean corr = true;
			for(int i=code.length-1;i>code.length-divisor.length+1;i--){
				if(temp_code[i]=='1'){
					corr=false;
					break;
				}
			}
			
			if(corr){
				
				out.println("SerVer Received the code Word Successfully! :) ");
				System.out.println("Code word is : " + new String(act_code));
			}
			else {
				System.out.println("There was an error in the codeword!");
				System.out.println("There is an error in code: " + new String(act_code));
				break;
			}
			client.close();
			
		}
		serversocket.close();
	}
	
}

