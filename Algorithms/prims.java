import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class prims{
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
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				cost[i][j]=infinity;
		int p,q,weight,min=9999,k=0,l=0;
		for(int i=0;i<e;i++){
			p=sc.nextInt();
			q=sc.nextInt();
			weight=sc.nextInt();
			if(weight<min){
				min=weight;
				k=p-1;l=q-1;
			}
			cost[p-1][q-1]=cost[q-1][p-1]=weight;
		}
		int near[]=new int[n];
		int ans[][]=new int[n-1][2];
		int minWeight=prims(cost,near,n,ans,k,l);
		System.out.println("MIN Weight is: "+minWeight);
		for(int i=0;i<n-1;i++)
		System.out.println(Arrays.toString(ans[i]));
		
	}
	
	
	static int prims(int[][] cost, int[] near, int n, int[][] ans,int k,int l) {
			ans[0][0]=k;ans[0][1]=l;
			int weight=cost[k][l];
			for(int i=0;i<n;i++){
				if(cost[i][k]<cost[i][l])
					near[i]=k;
				else
					near[i]=l;
			}
			near[k]=near[l]=0;
			int min=9999,temp=0;
			for(int i=1;i<n-1;i++){
				min=9999;
				for(int j=0;j<n;j++){
					if(near[j]!=0 && cost[j][near[j]]<min){
						min=cost[j][near[j]];
						temp=j;
					}
				}
				ans[i][0]=temp;
				ans[i][1]=near[temp];
				weight+=cost[temp][near[temp]];
				near[temp]=0;
				for(int m=0;m<n;m++){
					if(near[m]!=0 && cost[m][near[m]]>cost[m][temp]){
						near[m]=temp;
					}
				}
				
			}
		return weight;
	}
}