package pracks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mergesort {
	public static void merge(int ar[]){
		int size=1,l1=0,l2,u1,u2 = 0,n=ar.length,k,i,j;
		int sub[]=new int[25];
		while(size<n){
			l1=0;
			k=0;
			while((l1+size)<n){
				l2=l1+size;
				u1=l2-1;
				u2=((l2+size-1)<n)?(l2+size-1):(n-1);
				for(i=l1,j=l2;i<=u1 && j<=u2;k++){
					if(ar[i]<=ar[j])
						sub[k]=ar[i++];
					else
						sub[k]=ar[j++];
				}
				for(;i<=u1;k++)
					sub[k]=ar[i++];
				for(;j<=u2;k++)
					sub[k]=ar[j++];
				l1=u2+1;
			}
			    for(int p=l1;p<n;p++)
			    	sub[k++]=ar[p];
			    for(i=0;i<n;i++)
			    {
			    	ar[i]=sub[i];
			    }
				size *=2;
		}
	}
	public static void heap(int []ar,int n){
		int s,f,temp;
		for(int i=1;i<n;i++){
			temp=ar[i];
			s=i;
			f=(s-1)/2;
			while((s>0) && ar[f]<temp){
				ar[s]=ar[f];
				s=f;
				f=(s-1)/2;
			}
			ar[s]=temp;
		}
		for(int i=n-1;i>0;i--){
			temp=ar[i];
			ar[i]=ar[0];
			f=0;
			if(i==1) s=-1;
			else s=1;
			if((i>2) && ar[2]>ar[1]) s=2;
			while((s>=0) && ar[s]>temp){
				ar[f]=ar[s];
				f=s;
				s=2*f+1;
				if((s+1)<i && ar[s+1]>ar[s]) s=s+1;
				if(s>=i) s=-1;
			}
			ar[f]=temp;
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
		heap(ar,ar.length);
		System.out.println("Sorted Array is : ");
		for(int j=0;j<n;j++){
			System.out.print(ar[j]+" ");
		}
	}
}
