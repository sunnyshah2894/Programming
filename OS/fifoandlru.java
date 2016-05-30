import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

class fifops{
	String mem[];
	fifops(int n){
		mem=new String[n];
		for(int i=0;i<n;i++)
			mem[i]="O";
	}
	void fifo(String proc[]){
		int count=0;
		int hit=0;
		boolean hitted=false;
		for(int i=0;i<proc.length;i++){
			hitted=false;
			for(int j=0;j<mem.length;j++){
				if(mem[j].equals(proc[i])){
					hit++;
					hitted=true;
				}
			}
			if(hitted==false){
				mem[count++]=proc[i];
				if(count==mem.length)count=0;
				System.out.println(Arrays.toString(mem));
			}
			else{
			System.out.println(Arrays.toString(mem)+"HIT");
			}
		}
		System.out.println("Hit Ratio: "+ ((float)hit/(float)proc.length) );
	}
}

class lifos{
	
	String mem[];
	int age[];
	lifos(int n){
		mem=new String[n];
		age=new int[n];
		for(int i=0;i<n;i++)
			mem[i]="O";
	}
	
	void lifo(String proc[]){
		int count=0,hit=0;
		boolean hitted=false;
		for(int i=0;i<proc.length;i++){
			hitted=false;
			for(int j=0;j<mem.length;j++){
				if(!mem[j].equals("O")){
					age[j]++;
				}
				if(mem[j].equals(proc[i])){
					hit++;
					hitted=true;
					age[j]=1;
				}
			}
			if(hitted==false){
				boolean isbreak=false;
				for(int j=0;j<mem.length;j++){
					if(mem[j].equals("O")){
						mem[j]=proc[i];
						age[j]=1;
						isbreak=true;
						break;
					}
				}
				if(!isbreak){
					int max=0;
					int temp=0;
					for(int k=0;k<age.length;k++){
						if(max<age[k]){
							max=age[k];
							temp=k;
						}
					}
					age[temp]=1;
					mem[temp]=proc[i];
					
				}
				System.out.println(Arrays.toString(mem));
				System.out.println(Arrays.toString(age));
			}
			else{
				System.out.println(Arrays.toString(mem)+ "HIT");
				System.out.println(Arrays.toString(age));
			}
		}
		System.out.println("Hit Ratio: "+ (float)hit/(float)proc.length);
	}
	
}

public class fifoandlru {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The Memory size: ");
		int n=Integer.parseInt(br.readLine());
		fifops f=new fifops(n);
		System.out.println("Enter The No. of Process: ");
		int p=Integer.parseInt(br.readLine());
		String s[]=new String[p];
		System.out.println("Enter The Process: ");
		String str=br.readLine();
		s=str.split(" ");
		
		f.fifo(s);
		lifos l=new lifos(n);
		l.lifo(s);
	}
}
