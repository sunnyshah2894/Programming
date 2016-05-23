import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class fracknapsack {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter The NO Of Objects: ");
		int n=Integer.parseInt(br.readLine());
		int weights[]=new int[n];
		int profit[]=new int[n];
		float pbyw[]=new float[n];
		int object[]=new int[n];
		for(int i=0;i<n;i++){
			System.out.println("Enter WEIGHT and PROFIT of "+(i+1)+" object: ");
			String str=br.readLine();
			String s[]=str.split(" ");
			weights[i]=Integer.parseInt(s[0]);
			profit[i]=Integer.parseInt(s[1]);
			pbyw[i]=(float)((float)(weights[i])/(float)(profit[i]));
			object[i]=i+1;
			float temp=pbyw[i];     ///////InSertion Sort   .....
			int tempO=object[i];
			int tempP=profit[i];
			int tempW=weights[i];
			int j=i;
			while(j>0 && pbyw[j-1]<temp){
				pbyw[j]=pbyw[j-1];
				weights[j]=weights[j-1];
				profit[j]=profit[j-1];
				object[j]=object[j-1];
				j--;
			}
			pbyw[j]=temp;
			object[j]=tempO;
			weights[j]=tempW;
			profit[j]=tempP;
		}
		
		System.out.println("Enter the capacity of Knapsack: ");
		int capacity=Integer.parseInt(br.readLine());
		int m=0;
		float p=0;
		for(int i=n-1;i>=0;i--){
			
			if(m+weights[i]<=capacity){
				m+=weights[i];
				p+=profit[i];
				System.out.println("Take "+weights[i]+"Kg of Object "+object[i]);
			}
			else{
				p=p+(capacity-m)*(float)profit[i]/(float)weights[i];
				if(capacity-m>0)        //if capacity-m is zero then we should not use that object..
					System.out.println("Take "+(capacity-m)+"Kg of Object "+object[i]);
				break;
			}
		}
		System.out.println("YOUR PROFIT WILL BE "+p);
	}
}
