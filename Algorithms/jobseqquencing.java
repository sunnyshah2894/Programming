/*
 * Sunny Shah
 * SPIT 
 * JobSequencing(My method)
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class jobseqquencing {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The No. of Jobs: ");
		int n=sc.nextInt();
		int j=0;
		int jobsDead[]=new int[n];
		int jobsProfit[]=new int[n];
		int job[]=new int[n];
		int maxd=0;
		System.out.println("Enter The DeadLines And Profit of each Jobs: ");
		for(int i=0;i<n;i++){
			jobsDead[i]=sc.nextInt();
			jobsProfit[i]=sc.nextInt();
			j=i;
			job[i]=i+1;
			if(jobsDead[i]>maxd) maxd=jobsDead[i];
			int tempd=jobsDead[i];
			int tempp=jobsProfit[i];
			int tempj=job[i];
			while(j>0 && tempp>jobsProfit[j-1]){
				jobsProfit[j]=jobsProfit[j-1];
				jobsDead[j]=jobsDead[j-1];
				job[j]=job[j-1];
				j--;
			}
			jobsProfit[j]=tempp;
			jobsDead[j]=tempd;
			job[j]=tempj;
		}
		//System.out.println(Arrays.toString(jobsProfit));
		int ans[]=new int[maxd+1];
		int profit=0;
		ans[jobsDead[0]]=job[0];
		profit+=jobsProfit[0];
		for(int i=1;i<n;i++){
			for(j = jobsDead[i];j>=1;j--){
				if(ans[j]==0){
					ans[j]=job[i];
					profit+=jobsProfit[i];
					break;
				}
			}
		}
		Arrays.sort(ans);
		for(int i=1;i<ans.length;i++){
			System.out.print(ans[i]+ " ");
		}
		System.out.println("\nPROFIT= "+profit);
	}
}
