/* 
 * Sunny Shah
 * SPIT
 * GRAPH COLOURING 
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class graphcoloring {
	static int ans[];
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Number of Nodes: ");
		int n=sc.nextInt();
		System.out.println("Enter the no. of colours yo use: ");
		int m=sc.nextInt();
		System.out.println("Enter The no. of edges: ");
		int e=sc.nextInt();
		System.out.println("Enter the edges: ");
		int graph[][]=new int[n+1][n+1];
		for(int i=0;i<=n;i++)
			for(int j=0;j<=n;j++)
				graph[i][j]=0;
		int x,y;
		for(int i=0;i<e;i++){
			x=sc.nextInt();y=sc.nextInt();
			graph[x][y]=graph[y][x]=1;
		}
		ans=new int[n+1];
		graphcolour(graph,n,m,1);
		
	}

	static void graphcolour(int[][] graph, int n,int m,int k) {
		for(int i=1;i<=m;i++){
			if(promising(k,i,graph)){
				ans[k]=i;
				if(k==n){
					for(int l=1;l<=n;l++)
						System.out.println(ans[l] + " " );
				}
				else
					graphcolour(graph,n,m,k+1);
			}
		}
	}

	static boolean promising(int k, int m ,int graph[][]) {
		for(int i=1;i<k;i++){
			if(graph[k][i]==1 && ans[i]==m)
				return false;
		}
		return true;
	}
}
