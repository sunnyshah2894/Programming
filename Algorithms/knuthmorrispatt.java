/*
 * Sunny Shah
 * SPIT
 * Knuth Morris Pratt's Algorithm 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class knuthmorrispatt {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The Test String: ");
		String test=br.readLine();
		System.out.println("Enter The Pattern: ");
		String pattern=br.readLine();
		int pi[]=new int[pattern.length()];
		constructPiTable(pattern,pi);
		//System.out.println(Arrays.toString(pi));
		KMP(test,pattern,pi);
	}

	static void KMP(String test, String pattern, int[] pi) {
		int k=0;
		for(int i=0;i<test.length();i++){
			while(k>0 && test.charAt(i)!=pattern.charAt(k))		//since we have checked if j>0 hence j=pi[j-1]
				k=pi[k-1];                                     //or else we can have j=pi[j]
			if(test.charAt(i)==pattern.charAt(k))
				k++;
			//System.out.println(j);
			if(k==pattern.length()){
				System.out.println("Pattern found at " + (i-pattern.length()+1));
				return ;
			}
		}
		System.out.println("Pattern not found!");
		
	}

	static void constructPiTable(String pattern,int[] pi) {
			
		pi[0]=0;
		int k=0;
		for(int i=1;i<pattern.length();i++){
			
			while(k>0 && pattern.charAt(i)!=pattern.charAt(k))	//to calculate next k
				k=pi[k-1];
			
			if(pattern.charAt(k)==pattern.charAt(i))             //increment k 
				k=k+1;
			pi[i]=k;
		}
		
	}
	
}
