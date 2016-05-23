import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class nqueens {
	static int ans[];
	
	public static void main(String[] args)throws IOException {
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter The order of Chess Board: ");
		int n=sc.nextInt();
		ans=new int[n+1];
		nqueen(1,n);
	}
	 static void nqueen(int row,int n){
			for(int column=1;column<=n;column++){
				if(promising(row,column)){
					ans[row]=column;
					if(row==n)
						print();
					else{
						nqueen(row+1,n);
					}
				}
			}
		}
	 static void print() {
		 System.out.println();
		for(int i=1;i<ans.length;i++){
			System.out.println();
			for(int j=1;j<ans.length;j++){
				if(j==ans[i])
					System.out.print("(Q) ");
				else
					System.out.print("( ) ");
			}
		}
		
	}
	static boolean promising(int row , int column){
		 for(int i=1;i<row;i++){
			 
			 if(ans[i]==column)return false;
			 
			 if(Math.abs(ans[i]-column)==Math.abs(row-i))return false;
			 
		 }
		 return true;
	 }
  
	
}
