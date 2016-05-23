/*
 * Sunny Shah 
 * SPIT 
 * DIJKSTRA's ALGORITHM 
*/
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class dijkstra {
	static int cost[][];
	public static void main(String[] args)throws IOException {
		final int infinity=9999;
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter The no of Vertices: ");
		int n=sc.nextInt();
		System.out.println("Enter The no of Edges: ");
		int e=sc.nextInt();
		System.out.println("Enter The edges:(i j weight) ");
		cost=new int[n][n];
		for(int i=0;i<n;i++)				//  _
			for(int j=0;j<n;j++)			//   \
				cost[i][j]=infinity;		//	  \       initially cost[i][j] is kept infinity
		int p,q,weight;						//     \       
		for(int i=0;i<e;i++){				//      \      
			p=sc.nextInt();					//		---	gathering graph information :)
			q=sc.nextInt();					//      / 
			weight=sc.nextInt();			//	   /
			cost[p-1][q-1]=weight;			//	  /      add egdes to cost[i][j]
		}									//   7
		int dist[]=new int[n];
		dijstras(1,cost,dist,n);
		//for(int i=0;i<n;i++)
			//System.out.println(Arrays.toString(cost[i]));
		System.out.println(Arrays.toString(dist));
	}

	static void dijstras(int v, int[][] cost, int[] dist, int n) {
		boolean s[]=new boolean[n];
		
		for(int i=0;i<n;i++){
			s[i]=false;						//making visited of all nodes as false
			dist[i]=cost[v-1][i];           //update distance array to distance from start node
		}
		s[v-1]=true;						//now start node is visited
		dist[v-1]=0;						//do or not do no need... *_*
		
		for(int i=1;i<n;i++){
			int min=9999;int q=0;
			for(int j=0;j<n;j++){ 			//finding which unvisited node has min distance
				if(dist[j]<min && !s[j]){
					min=dist[j];
					q=j;
				}
			}
			//System.out.println(Arrays.toString(dist));
			s[q]=true;
			for(int k=0;k<n;k++){			//updating the distance array 
				if(dist[k]>(dist[q]+cost[q][k]) && !s[k]){   	//if initial dist is greater than sum of 
					dist[k]=dist[q]+cost[q][k];					//dist. of q(which is its min) + cost[q][k]
				}												//NOTE: [q][k]	imp as graph is directed					
			}
			
		}
		
	}
	
}
