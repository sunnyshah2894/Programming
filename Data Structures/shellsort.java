package pracks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class shellsort {
	public static void shell(int ar[],int inc[]){
		int span,k;
		for(int i=0;i<inc.length;i++){
			span=inc[i];
			for(int j=0;j<ar.length-span;j++){
				int temp=ar[j+span];
				k=j;
				while(k>=0  && ar[k]>temp){
					ar[k+span]=ar[k];
					k=k-span;
				}
				k+=span;
				ar[k]=temp;
			}
		}
	}
	public static void main(String [] arg) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The No. Of Elements: ");
		int n=Integer.parseInt(br.readLine());
		int ar[]=new int[n];
		for(int i=0;i<n;i++){
			System.out.println("Enter: ");
			ar[i]=Integer.parseInt(br.readLine());
			}
		int inc[]={5,3,1};
		shell(ar,inc);
		System.out.println("Sorted Array is : ");
		for(int j=0;j<n;j++){
			System.out.print(ar[j]+" ");
		}
	}
}
