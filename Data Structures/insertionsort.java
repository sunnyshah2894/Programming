package pracks;
import java.io.*;
public class insertionsort {

	public static void insertion(int ar[]){
		int temp;
		for(int i=1;i<ar.length;i++){
			int j=i-1;
			temp=ar[i];
			while(j>=0 && ar[j]>temp){
				ar[j+1]=ar[j];
				j--;
			}
			j++;
			ar[j]=temp;
		}
	}
	public static void selection(int ar[]){
		int large,index;
		for(int i=ar.length-1;i>0;i--){
			index=0;
			large=ar[index];
			for(int j=1;j<=i;j++){
				if(ar[j]>ar[index]){
					index=j;
				}
			}
			large=ar[index];
			ar[index]=ar[i];
			ar[i]=large;
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
		selection(ar);
		System.out.println("Sorted Array is : ");
		for(int j=0;j<n;j++){
			System.out.print(ar[j]+" ");
		}
	
	}
	
}
