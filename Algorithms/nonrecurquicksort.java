/*
 * Sunny Shah
 * SPIT
 * Non-Recursive Quick Sort
 * Same as quick sort. Here instead of recursively calling qicksort method we will save the calling instances in a 
 *  						STACK 
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class nonrecurquicksort {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The no of elements in Array: ");
		int n=sc.nextInt();
		int ar[]=new int[n];
		System.out.println("Enter The Elements: ");
		for(int i=0;i<n;i++){
			ar[i]=sc.nextInt();
		}
		
		quicksort(0,n-1,ar);
		System.out.println(Arrays.toString(ar));
	}

	static void quicksort(int lb, int ub, int[] ar) {
		
		Stack<Integer> s=new Stack<Integer>();
		s.push(ub);						//  This is important since to  
		s.push(lb);						//  let it enter the while loop below
		while(!s.isEmpty()){
			lb=(Integer) s.pop();	// AUTOMATIC 
			ub=(Integer)s.pop();    //  UNBOXING from Integer object to int
			
			if(lb>=ub)continue;        // i.e no or 1 element only then oops it is sorted '~' 
			
			int j=partition(lb,ub,ar);  //find partition....
			
			s.push(j-1);   //  Left partition part 
			s.push(lb);    //   save in stack
			
			s.push(ub);     //  Right partition part
			s.push(j+1);    //  save in stack
			
		}
		
		
	}

	static int partition(int lb, int ub, int[] ar) {
		int up=ub;
		int down=lb;
		int pivot=ar[lb];
		int temp=lb;
		if((ub-lb)>=4){										//if subarray has more than 5 elements than 
			temp=new Random().nextInt((ub-lb+1)) + lb;
			pivot=ar[temp];
			ar[temp]=ar[lb];
			ar[lb]=pivot;
		}
	
	//NOTE: That randomly choosen pivot should be swapped with lowerbound element since it is possible that pivot 
	//element position may change during below (while(down<up)..) operation..
		
		
		while(down<up){
			
			while(ar[down]<=pivot && down<up )
				down++;
			while(ar[up]>pivot)
				up--;
			
			if(down<up){
				int dim=ar[down];
				ar[down]=ar[up];
				ar[up]=dim;
			}		
		}
		ar[lb]=ar[up];
		ar[up]=pivot;
		return up;
	}
}