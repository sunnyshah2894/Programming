/*
 * Sunny Shah
 * SPIT
 * LONGEST COMMON SUBSEQUENCE 
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class lcs {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Text String: ");
		String text=sc.nextLine();
		System.out.println("Enter the Patter: ");
		String pattern=sc.nextLine();
		int values[][]=new int[pattern.length()+1][text.length()+1];
		int arrow[][]=new int[pattern.length()][text.length()];
		for(int i=0;i<text.length()+1;i++)
			values[0][i]=0;
		for(int i=0;i<pattern.length()+1;i++)
			values[i][0]=0;
		//  .       . 
		// '|'3 <-1 '\`2 
		for(int i=1;i<pattern.length()+1;i++){
			for(int j=1;j<text.length()+1;j++){
				
				if(pattern.charAt(i-1)==text.charAt(j-1)){
					arrow[i-1][j-1]=2;
					//System.out.println(text.charAt(j-1));
					values[i][j]=values[i-1][j-1]+1;
				}
				else{
					if(values[i-1][j]>=values[i][j-1]){   // | arrow
						arrow[i-1][j-1]=3;
						values[i][j]=values[i-1][j];
					}
					else{
						arrow[i-1][j-1]=1;
						values[i][j]=values[i][j-1];
					}
				}
			}
		}
		String str="";
		for(int i=pattern.length();i>0;i--){
			for(int j=text.length();j>0;j--){
				
				if(arrow[i-1][j-1]==2){
					str+=text.charAt(j-1)+"";
					i--;
					if(i==0)break;
				}
				else if(arrow[i-1][j-1]==3){
					i--;
					if(i==0)break;
					j++;
				}
			}
		}
		StringBuffer s=new StringBuffer(str);
		s=s.reverse();
		System.out.println("Longest Common Subsequence: "+s);
	}
}
