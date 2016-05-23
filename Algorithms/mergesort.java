/*
 * Sunny Shah
 * SPIT
 * MERGE SORT USING DIVIDE AND CONQUER
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class mergesort {
	
	static int ar[];
	
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The no of elements in Array: ");
		int n=sc.nextInt();
		ar=new int[n];
		System.out.println("Enter The Elements: ");
		for(int i=0;i<n;i++){
			ar[i]=sc.nextInt();
		}
		
		mergesort(0,n-1);
		System.out.println(Arrays.toString(ar));
	}

	static void mergesort(int lb, int ub) {
		if(lb>=ub)
			return;
		else{
			int mid=(ub+lb)/2;
			mergesort(lb,mid);
			mergesort(mid+1,ub);
			merge(lb,ub,mid);
		}
		
	}

	static void merge(int lb, int ub,int mid) {
		
		int i=lb,j=mid+1,k=0;
		int temp[]=new int[ub-lb+1];
		
		while(i<=mid && j<=ub){        //need no explanation HOPE!.. :)
			if(ar[i]<ar[j])
				temp[k++]=ar[i++];
			else
				temp[k++]=ar[j++];
		}
		
		while(i<=mid)
			temp[k++]=ar[i++];
		while(j<=ub)
			temp[k++]=ar[j++];
		
		j=lb;
		for(i=0;i<ub-lb+1;i++){
			ar[j++]=temp[i];
		}
		
		
	}
}
