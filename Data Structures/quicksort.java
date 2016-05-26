package pracks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class quicksort {

	public static int partition(int lb,int ub,int ar[]){
		int pivot=ar[lb];
		int up=ub,down=lb;
		while(up>down){
			while(ar[down]<=pivot && down<up)
				down++;
			while(ar[up]>pivot )
				up--;
			if(down<up){
				int temp=ar[down];
				ar[down]=ar[up];
				ar[up]=temp;
			}
		}
		ar[lb]=ar[up];
		ar[up]=pivot;
		return up;
	}
	public static void quick(int lb,int ub,int ar[])
	{
		if(lb>=ub)
			return;
		int j=partition(lb,ub,ar);
		quick(lb,j-1,ar);
		quick(j+1,ub,ar);
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
		quick(0,ar.length-1,ar);
		System.out.println("Sorted Array is : ");
		for(int j=0;j<n;j++){
			System.out.print(ar[j]+" ");
		}
	}
}
