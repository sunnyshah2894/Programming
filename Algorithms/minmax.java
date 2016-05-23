import java.io.IOException;
import java.util.Scanner;


public class minmax {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The number of Elements: ");
		int n=sc.nextInt();
		int ar[]=new int[n];
		System.out.println("Enter The Array of Numbers: ");
		for(int i=0;i<n;i++)
			ar[i]=sc.nextInt();
		int min=getMin(ar,0,n-1);
		System.out.println(min);
	}
		//same for max..
	
	static int getMin(int[] ar,int lb,int ub) {
		if(ub-lb<=1){
			if(ar[lb]<ar[ub])return ar[lb];
			else return ar[ub];
		}
		else{
			int mid=(ub-lb)/2;
			int t1=getMin(ar,lb,lb+mid);
			int t2=getMin(ar,lb+mid+1,ub);
			if(t1>t2)return t2;
			else return t1;
		}
	}	
}
