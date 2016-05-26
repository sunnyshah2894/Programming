package pracks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class linearprobing {
	public static void main(String [] arg) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The Table Size: ");
		int nt=Integer.parseInt(br.readLine());
		Object hashTable[]=new Object[nt];
		System.out.println("Enter The No. Of Elements: ");
		int n=Integer.parseInt(br.readLine());
		int ar[]=new int[n];
		for(int i=0;i<n;i++){
			System.out.println("Enter: ");
			ar[i]=Integer.parseInt(br.readLine());
		}
		for(int i=0;i<n;i++){
			int pos=ar[i]%nt+1;
			if(pos==nt)pos=0;
			boolean collision=false;
			while(hashTable[pos]!=null){
				pos++;collision=true;
				if(pos==nt)pos=0;
			}
			hashTable[pos]=ar[i];
			System.out.println(ar[i]+"   "+pos+"   "+collision);
			
		}
	}
}
