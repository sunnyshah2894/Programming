package pracks;
import java.io.*;
class stack1{
	int ar[]=new int[20];
	int top=-1;
	public void push(int x){
		if(top==9)
			System.out.println("Stack overflow");
		else 
			ar[++top]=x;
	}
	public int pop(){
		if(top==-1){
			System.out.println("Stack underflow");
			return -1;}
		else 
			return ar[top--];
		}
	public boolean isEmpty(){
		return (top==-1);
	}
    public int peek(){
    	if(!isEmpty())
    	    return ar[top];
    	else
    		return -1;
    }
}
public class dtb {

	public static void main(String []srg)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ENTER THE DECIIMAL NO.: ");
		int dec=Integer.parseInt(br.readLine());
		int rem=dec;
		stack1 s=new stack1();
		while(dec!=0){
			rem=dec%2;
			dec=dec/2;
			s.push(rem);
		}
		System.out.println("THE BINARY EQUIVALENT IS: ");
		while(!s.isEmpty()){
			System.out.print(s.pop());
		}
	}
}
