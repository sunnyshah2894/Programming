import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class kruskal {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no. of Nodes: ");
		int n=sc.nextInt();
		System.out.println("Enter no. of Edges: ");
		int e=sc.nextInt();
		int j=0;
		int graph[][]=new int[e][3];
		int connect[]=new int[n];
		for(int i=0;i<n;i++)
			connect[i]=i+1;
		System.out.println("Enter the Connected Vertices And Their Weight: ");
		for(int i=0;i<e;i++){
			graph[i][0]=sc.nextInt()-1;
			graph[i][1]=sc.nextInt()-1;
			graph[i][2]=sc.nextInt();
			
			int temp0=graph[i][0];
			int temp1=graph[i][1];
			int temp2=graph[i][2];
			
			j=i;
			while(j>0 && temp2<graph[j-1][2] ){
				graph[j][2]=graph[j-1][2];
				graph[j][1]=graph[j-1][1];
				graph[j][0]=graph[j-1][0];
				j--;
			}
			graph[j][2]=temp2;
			graph[j][1]=temp1;
			graph[j][0]=temp0;
			
		}
		
		int ans[][]=new int[n-1][2];
		int minWeight=0;
		j=0;
		for(int i=0 ; i<e && j<n-1 ; i++){
			System.out.println((graph[i][0]+1)+" "+connect[graph[i][0]]+ (graph[i][1]+1)+" "+ connect[graph[i][1]]);
			if(connect[graph[i][0]]!=0 && connect[graph[i][1]]!=0 && connect[graph[i][0]]!=connect[graph[i][1]]){
				System.out.println((graph[i][0]+1)+" "+connect[graph[i][0]]+ (graph[i][1]+1)+" "+ connect[graph[i][1]]);
				ans[j][0]=graph[i][0]+1;
				ans[j++][1]=graph[i][1]+1;
				minWeight+=graph[i][2];
				connect[graph[i][0]]=graph[i][1];
				connect[graph[i][1]]=graph[i][0];
			}
			if(connect[graph[i][0]]==0 || connect[graph[i][1]]==0){
				
				ans[j][0]=graph[i][0]+1;
				ans[j++][1]=graph[i][1]+1;
				minWeight+=graph[i][2];
				if(connect[graph[i][0]]==0)
					connect[graph[i][1]]=connect[graph[i][1]];
				else
					connect[graph[i][0]]=connect[graph[i][1]];
				System.out.println((graph[i][0]+1)+" "+connect[graph[i][0]]+ (graph[i][1]+1)+" "+ connect[graph[i][1]]);
			}
		}
		System.out.println("Minimum Weight is: " + minWeight );
		for(int i=0;i<n-1;i++)
			System.out.println(Arrays.toString(ans[i]));
		
	}
}
