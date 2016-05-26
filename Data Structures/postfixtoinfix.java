package pracks;
import java.io.*;
class stack{
	Object ar[]=new Object[20];
	int top=-1;
	public void push(Object x){
		if(top==19)
			System.out.println("stack overflow");
		else{
			ar[++top]=x;
		}
	}
	public Object pop(){
		if(top==-1){
			System.out.println("stack underflow");
			return null;}
		else{
			return ar[top--];
		}
	}
	public boolean isEmpty(){
		return (top==-1);
	}
}
public class postfixtoinfix {
	public static void main(String [] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ENTER THE POSTFIX STRING: ");
		String post=br.readLine();
		stack s=new stack();
		String a,b;
		for(int i=0;i<post.length();i++){
			if(isoperand(post.charAt(i)))
				s.push(""+post.charAt(i));
			else{
				b=(String)s.pop();
				a=(String)s.pop();
				String str="("+a+post.charAt(i)+b+")";
				s.push(str);
			}
		}
		String infix=(String)s.pop();
		if(s.isEmpty()){
		System.out.println(infix);}
		else
			System.out.println("Invalid Expression!");
	}
	static boolean isoperand(char c)
	{
		if(c=='+'||c=='-'||c=='*'||c=='/'||c=='^'||c=='('||c==')')
		{
			return false;
		}
		else return true;
	}

}
