import java.io.IOException;
import java.util.Scanner;


public class FCFS {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The Starting position: ");
		int start=sc.nextInt();
		System.out.println("Enter The Number of Requsts: ");
		int r=sc.nextInt();
		int req[]=new int[r];
		System.out.println("Enter The Cylinder Numbers: ");
		for(int i=0;i<r;i++){
			req[i]=sc.nextInt();
		}
		diskfcfs(start,req);
		disksstf(start,req);
		
	}

	static void disksstf(int start, int[] req) {
		int total=0;
		int min;
		int temp=0;
		boolean visited[]=new boolean[req.length];
		System.out.println("************SSTF***************");
		for(int i=0;i<req.length;i++){
			min=1000;
			for(int j=0;j<req.length;j++){
				if(!visited[j] && Math.abs(start-req[j])<min){
					min=Math.abs(start-req[j]);
					temp=j;
				}
			}
			total+=min;
			start=req[temp];
			visited[temp]=true;
		}
		System.out.println("Total Head Movement: "+total);
	}

	static void diskfcfs(int start, int[] req) {
		int total=0;
		System.out.println("************FCFS***************");
		for(int i=0;i<req.length;i++){
			total=total+Math.abs(req[i]-start);
			start=req[i];
		}
		System.out.println("Total Head Movement: "+total);
	}
}
