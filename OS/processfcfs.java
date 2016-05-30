import java.io.IOException;
import java.util.Scanner;


public class processfcfs {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the no. of Processes: ");
		int n=sc.nextInt();
		int p[]=new int[n];
		int a[]=new int[n];
		int o[]=new int[n];
		int j=0;
		for(int i=0;i<n;i++){
			System.out.println("Enter the Arrival And Burst Time of Process "+(i+1)+": ");
			o[i]=i+1;
			a[i]=sc.nextInt();
			p[i]=sc.nextInt();
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
		System.out.println("EXECUTION OF PROCESSES WILL BE AS: ");
		int time=0,turntime=0;
		for(int i=0;i<n;i++){
			System.out.println("Process "+ o[i]+ " will exceuted from "+time+ " to "+(time+p[i])+ " with Turnaround time "+((time-a[i])+p[i]));
			turntime+=(time-a[i])+p[i];
			time+=p[i];
		}
		System.out.println("Average turnAround time is: "+(float)turntime/(float)n);
		
	}
}
