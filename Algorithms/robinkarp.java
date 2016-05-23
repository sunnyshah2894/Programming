/*
 * Sunny Shah
 * SPIT
 * ROBIN KARP's String Matching Algorithm
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class robinkarp {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The Test String: ");
		String test=br.readLine();
		System.out.println("Enter The Pattern: ");
		String pattern=br.readLine();
		RK(test,pattern);
		
	}

	static void RK(String test, String pattern) {
		long hashPattern=0;
		long m=0;
		long q=122222;
		long h=(long) Math.pow(10, pattern.length()-1);
		for(int i=0;i<pattern.length();i++){
			hashPattern=(10*hashPattern + pattern.charAt(i));  //calculating hash value of pattern 
			m=(m*10 + test.charAt(i));						  //calculating hash value of substring of text	
		}
		//System.out.println(hashPattern+"   "+m);
		for(int s=0;s<=test.length()-pattern.length();s++){
			if(m==hashPattern){                               //if both hash values are same then pattern found
				System.out.println("Pattern found at "+s);    //YIPEE!  "_"       
				return;
			}	   // my text 6722329  pattern lenght was 3 then m was initially 672 but m and hashpattern
			else{  // are not same so we now need next substring i.e. 722 hence can be find as ((672-6*100)*10+2)					 
				m=((m-test.charAt(s)*h)*10 )+ test.charAt(s+pattern.length()); //hence the formula   
			}
			//System.out.println(hashPattern+"   "+m);
		}
		//if(m==hashPattern)
			//System.out.println("Pattern found at 0");
		System.out.println("Pattern not found");
		
	}
}