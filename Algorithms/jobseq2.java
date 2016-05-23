/*
 * Sunny Shah 
 * SPIT 
 * JOB SEQUENCING(SAHANI METHOD)
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class jobseq2 {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The No. of Jobs: ");
		int n=sc.nextInt();
		int j=0;
		int d[]=new int[n+1];
		int p[]=new int[n];
		int job[]=new int[n];
		int maxd=0;
		System.out.println("Enter The DeadLines And Profit of each Jobs: ");
		for(int i=0;i<n;i++){
			d[i+1]=sc.nextInt();
			p[i]=sc.nextInt();
			j=i;
			job[i]=i+1;
			if(d[i]>maxd) maxd=d[i];
			//int tempd=jobsDead[i];
			int tempp=p[i];
			int tempj=job[i];
			while(j>0 && tempp>p[j-1]){
				p[j]=p[j-1];
				//jobsDead[j]=jobsDead[j-1];
				job[j]=job[j-1];
				j--;
			}
			p[j]=tempp;
			//jobsDead[j]=tempd;
			job[j]=tempj;
		}
		
		int ans[]=new int[maxd+1];
		ans[0]=0;
		d[0]=0;
		ans[1]=job[0];
		int count=1;
		int profit=p[0];
		for(int i=1;i<n;i++){
			int preplace=count;
			//preplace points to place previous to current empty pos.
			
			//checking two things is needed now i.e. the deadline of job at preplace should not be equal to preplace
			//this is to check whether the job at preplace is just executing at its deadline if yes than its deadline 
			//will be equal to preplace hence we cannot place new job before that or on that position
			//second condition is that deadline of job at preplace should be less than that of new one if it is 
			//greater than that than we move the at preplace to right......hence preplace--
			
			while(d[ans[preplace]]!=preplace && d[job[i]]<d[ans[preplace]] )
				preplace--;
			
			//now preplace is at a position after which we can atleast place new job
			//but we have to check whether deadline of new job is >=(preplace+1) ie >preplace
			//also it should be greater than previous job's deadline.....
			
			if(d[job[i]]>=d[ans[preplace]] && d[job[i]]>preplace){
				for(int j1=count;j1>=preplace+1;j1--)
					ans[j1+1]=ans[j1];
				ans[preplace+1]=job[i];
				profit+=p[i];
				count++;				
			}
			
		}
		System.out.println("Execution of Jobs should be as: ");
		for(int i=1;i<=maxd;i++){
			System.out.print(ans[i]+" ");
		}
		System.out.println("\nProfit: "+profit);
		
	}
}