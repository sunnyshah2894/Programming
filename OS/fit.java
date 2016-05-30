import java.io.IOException;
import java.util.*;

class fit{
	static int[] block;
	static int[] mprog;
	
	public static void main(String[] args)throws IOException{
		int n, m;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter no of places: ");
		n = sc.nextInt();
		block = new int[n];
		System.out.println("Enter places separated by spaces: ");
		for(int i=0; i<n; i++){
			block[i] = sc.nextInt();
		}
		System.out.print("Enter no of inputs: ");
		m = sc.nextInt();
		mprog = new int[m];
		System.out.println("Enter inputs separated by spaces: ");
		for(int i =0; i<m; i++){
			mprog[i] = sc.nextInt();
		}
		//firstFit(block, mprog);
		//bestFit(block, mprog);
		nextFit(block, mprog);
	}
	
	static void firstFit(int[] block, int[] mprog){
		boolean isbreak=false;
		System.out.println("*************FIRST FIT************************");
		for(int i=0;i<mprog.length;i++){
			isbreak=false;
			for(int j=0;j<block.length;j++){
				if(block[j]-mprog[i]>=0){
					System.out.println("Program "+(i+1)+" is allocated to Block "+(j+1));
					isbreak=true;
					block[j]-=mprog[i];
					break;
				}
			}
			if(!isbreak)
				System.out.println("Program "+(i+1)+" cannot be allocaed any block");
		}
	}
	
	static void bestFit(int[] block, int[] mprog){
		boolean isbreak=false;
		System.out.println("*************BEST FIT*************************");
		int close=1000;
		int temp=0;
		for(int i=0;i<mprog.length;i++){
			close=1000;
			isbreak=false;
			for(int j=0;j<block.length;j++){
				if(block[j]-mprog[i]>=0 && block[j]-mprog[i]<close){
					close=block[j]-mprog[i];
					isbreak=true;
					temp=j;
				}
			}
			if(!isbreak)
				System.out.println("Program "+(i+1)+" cannot be allocated any block");
			else{
				System.out.println("Program "+(i+1)+" is allocated to Block "+(temp+1));
				block[temp]-=mprog[i];
				//System.out.println(Arrays.toString(block));
			}
		}
	}
	
	static void nextFit(int[] block, int[] mprog){
		boolean isbreak=false;
		int start=-1;
		System.out.println("*************NEXT FIT*************************");
		for(int i=0;i<mprog.length;i++){
			isbreak=false;
			for(int j=(start+1)%block.length;j!=start;j=(j+1)%block.length){
				if(block[j]-mprog[i]>=0){
					block[j]-=mprog[i];
					isbreak=true;
					start=j;
					System.out.println("Program "+(i+1)+" is allocated to Block "+(j+1));
					break;
				}
			}
			if(!isbreak){
				System.out.println("Program cannot be allocated to any block");
			}
			
		}
		
	}
}