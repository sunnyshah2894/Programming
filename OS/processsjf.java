import java.io.IOException;
import java.util.Scanner;


public class processsjf {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the no. of Processes: ");
		int n=sc.nextInt();
		int p[]=new int[n];
		int a[]=new int[n];
		int o[]=new int[n];
		int j=0;
		int sum=0;
		for(int i=0;i<n;i++){
			System.out.println("Enter the Arrival And Burst Time of Process "+(i+1)+": ");
			o[i]=i+1;
			a[i]=sc.nextInt();
			p[i]=sc.nextInt();
			sum+=p[i];
			j=i;
			int tempa=a[i];
			int tempp=p[i];
			int tempo=o[i];
			while(j>0 && a[j-1]>tempa){
				a[j]=a[j-1];
				p[j]=p[j-1];
				o[j]=o[j-1];
				j--;
			}
			a[j]=tempa;
			p[j]=tempp;
			o[j]=tempo;
		}
		int min=1000;
		int t=0;
		int to=0;
		for(int time=0;time<sum;time++){
			min=1000;
			for(int i=0;i<n;i++){
				if(p[i]!=0 && a[i]<=time && min>p[i]){
					min=p[i];
					t=i;
				}
			}
			System.out.println("Time "+time+": "+"Program "+o[t]+" will execute ");
			p[t]-=1;
			if(p[t]==0){
				to+=time+1-a[t];
			}
		}
		System.out.println("Average Turnaround time: "+(float)to/(float)n);
		
	}
}