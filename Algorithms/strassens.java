/*
 * Sunny Shah
 * SPIT 
 * Strassen's Matrix Multiplication
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class strassens {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The Order Of Matrix: ");
		int n=sc.nextInt();
		int a[][]=new int[n][n];
		int b[][]=new int[n][n];
		for(int i=0;i<n;i++){
			System.out.println("Enter The Row "+(i+1)+" of Matrix A: ");
			for(int j=0;j<n;j++){
				a[i][j]=sc.nextInt();
			}
		}
		for(int i=0;i<n;i++){
			System.out.println("Enter The Row "+(i+1)+" of Matrix B: ");
			for(int j=0;j<n;j++){
				b[i][j]=sc.nextInt();
			}
		}
		int c[][]=new int[n][n];
		matrixmultiply(n,a,b,c);
		print(n,c);
	}

	static void print(int n,int[][] c){
		for(int i=0;i<n;i++)
			System.out.println(Arrays.toString(c[i]));
	}
	static void matrixAdd(int n,int [][]a ,int b[][],int c[][]){
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				c[i][j]=a[i][j]+b[i][j];
	}
	static void matrixSubtract(int n,int [][]a ,int b[][],int c[][]){
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++){
				c[i][j]=a[i][j]-b[i][j];
			}
	}
	static void breakmat(int part,int ar[][],int d[][]){
		int starti=0,startj=0;
		if(part==1)
			starti=0;startj=0;
		if(part==2)
			startj=ar.length/2;
		if(part==3)
			starti=ar.length/2;
		if(part==4){
			starti=ar.length/2;
			startj=ar.length/2;					
		}
		for(int i=starti;i<starti+ar.length/2;i++)
			for(int j=startj;j<startj+ar.length/2;j++)
				d[i-starti][j-startj]=ar[i][j];
		
	}
	static void matrixmultiply(int n, int[][] a, int[][] b, int[][] c) {
		if(n==1){
			c[0][0]=a[0][0]*b[0][0];
		}
		else{
			
			
			int a11[][],a12[][],a21[][],a22[][],b11[][],b12[][],b21[][],b22[][];
			a11=new int[n/2][n/2];a12=new int[n/2][n/2];a21=new int[n/2][n/2];a22=new int[n/2][n/2];
			b11=new int[n/2][n/2];b12=new int[n/2][n/2];b22=new int[n/2][n/2];b21=new int[n/2][n/2];
			breakmat(1,a,a11);
			breakmat(2,a,a12);
			breakmat(3,a,a21);
			breakmat(4,a,a22);
			breakmat(1,b,b11);
			breakmat(2,b,b12);
			breakmat(3,b,b21);
			breakmat(4,b,b22);	
			
			
			int a1[][],a2[][],a3[][],a4[][],a5[][],b1[][],b2[][],b3[][],b4[][],b5[][];
			a1=new int[n/2][n/2];a2=new int[n/2][n/2];a3=new int[n/2][n/2];a4=new int[n/2][n/2];
			a5=new int[n/2][n/2];b1=new int[n/2][n/2];b2=new int[n/2][n/2];b3=new int[n/2][n/2];
			b4=new int[n/2][n/2];b5=new int[n/2][n/2];
			matrixAdd(n/2,a11,a22,a1);
			matrixAdd(n/2, a21,a22, a2);
			matrixAdd(n/2,b11,b22,b1);
			matrixAdd(n/2,a11,a12,a3);
			matrixSubtract(n/2, a21,a11, a4);
			matrixSubtract(n/2,a12,a22,a5);
			matrixSubtract(n/2,b12,b22,b2);
			matrixSubtract(n/2,b21,b11,b3);
			matrixAdd(n/2,b12,b11,b4);
			matrixAdd(n/2,b21,b22,b5);
			
			int s1[][],s2[][],s3[][],s4[][],s5[][],s6[][],s7[][];
			s1=new int[n/2][n/2];s2=new int[n/2][n/2];s3=new int[n/2][n/2];s4=new int[n/2][n/2];
			s5=new int[n/2][n/2];s6=new int[n/2][n/2];s7=new int[n/2][n/2];
			matrixmultiply(n/2, a1, b1, s1);
			matrixmultiply(n/2, a2, b11, s2);
			matrixmultiply(n/2, a11, b2, s3);
			matrixmultiply(n/2, a22, b3, s4);
			matrixmultiply(n/2, a3, b22, s5);
			matrixmultiply(n/2, a4, b4, s6);
			matrixmultiply(n/2, a5, b5, s7);
			int c1[][],c2[][],c3[][],c4[][];
			c1=new int[n/2][n/2];c2=new int[n/2][n/2];c3=new int[n/2][n/2];c4=new int[n/2][n/2];
			int t1[][],t2[][],t3[][],t4[][];
			t1=new int[n/2][n/2];t2=new int[n/2][n/2];t3=new int[n/2][n/2];t4=new int[n/2][n/2];
		
			matrixAdd(n/2, s1, s4, t1);
			matrixSubtract(n/2,s7,s5,t2);
			matrixAdd(n/2,t1,t2,c1);
			matrixAdd(n/2,s3,s5,c2);
			matrixAdd(n/2,s4,s2,c3);
			matrixAdd(n/2,s1,s3,t3);
			matrixSubtract(n/2,s6,s2,t4);
			matrixAdd(n/2,t3,t4,c4);
			matrixcombine(n,c1,c2,c3,c4,c);
			
		}
		
	}

	static void matrixcombine(int n, int[][] c1, int[][] c2,int[][] c3, int[][] c4,int[][] ans) {
		for(int i=0;i<n/2;i++)
			for(int j=0;j<n/2;j++)
				ans[i][j]=c1[i][j];
		for(int i=n/2;i<n;i++)
			for(int j=0;j<n/2;j++)
				ans[i][j]=c3[i-n/2][j];
		for(int i=0;i<n/2;i++)
			for(int j=n/2;j<n;j++)
				ans[i][j]=c2[i][j-n/2];
		for(int i=n/2;i<n;i++)
			for(int j=n/2;j<n;j++)
				ans[i][j]=c4[i-n/2][j-n/2];
	}
}
