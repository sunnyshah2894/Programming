/*
 * Sunny Shah
 * SPIT
 * Randomized Quick Sort
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class randquicksort {
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
		if(lb>=ub){
			return;                //ie one or none elements left
		}
		else{
			int j=partition(lb,ub,ar);
			quicksort(lb,j-1,ar);                //applying quicksort on left part
			quicksort(j+1,ub,ar);				 //applying quicksort on right part
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
				ar[down]=ar[down] + ar[up] - (ar[up]=ar[down]);
			}		
		}
		ar[lb]=ar[up];
		ar[up]=pivot;
		return up;
	}
}
