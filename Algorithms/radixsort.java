/*
 * Sunny Shah
 * SPIT
 * RADIX SORT 
 */ 
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;


public class radixsort {
	
	static int ar[];
	
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The no of elements in Array: ");
		int n=sc.nextInt();
		ar=new int[n];
		System.out.println("Enter The Elements: ");
		int max=1;
		for(int i=0;i<n;i++){
			ar[i]=sc.nextInt();
			String str=ar[i]+"";
			if(str.length()>max) max=str.length();
		}
		radixsort(ar,max);
		System.out.println(Arrays.toString(ar));
	}

	static void radixsort(int[] ar, int max ) {
		Vector<Integer> v[]=new Vector[10];    //
		for(int i=0;i<10;i++){					//  INITIALIZING VECTOR
			v[i]=new Vector<Integer>();			//
		}										//
		int d=1;
		int temp=0;
		for(int i=0;i<max;i++){
			temp=ar[i];
			for(int j=0;j<ar.length;j++){      // taking the ith digit from lsb in ith iteration 
				temp=ar[j]/d;				   //and arranging in vectors accordingly	
				v[temp%10].add(ar[j]);		  //i.e if digit is 6 then add that number to v[6] and so on...
			}
			int count=0;
			for(int k=0;k<10;k++){
				for(int j=0;j<v[k].size();j++){      //now take all numbers from each vector starting with v[0] to
					ar[count++]=(Integer)v[k].get(j);  // v[9] and adding them array again hence they are sorted 
				}									   // according to ith digit from lsb	 					  
				v[k].clear();						   //clear vectors for next use....
			}
			d=d*10;                             
		}
		
		
	}
}