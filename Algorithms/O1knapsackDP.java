/*
 * Sunny Shah
 * SPIT
 * O1 KNAPSACK USING DYNAMIC PROGRAMMING
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
class O1knapsackDP
{
	public static void main (String[] args)throws IOException
	{
		Scanner br=new Scanner(System.in);
		
		
		int tc=br.nextInt();
		System.out.println("Enter the Capacity of Knapsack : ");
		int n0 = 0;
		int CAP=0;
		int wt[]=new int[no];
		int profit[]=new int[no];
		int Knapsack[][]=new int[CAP+1][no+1];
		
		while(tc-->0){
				no=br.nextInt();
				CAP=br.nextInt();
				int c[] = new int[no],
				for(int i=0;i<no;i++)
				
				for(int ITEM=1;ITEM<=no;ITEM++)
				{
					for(int capacity=1;capacity<=CAP;capacity++)
					{
						if(capacity>=wt[ITEM-1])
							Knapsack[capacity][ITEM]=MAX(Knapsack[capacity][ITEM-1],Knapsack[capacity-wt[ITEM-1]][ITEM-1]+profit[ITEM-1]);
						else
							Knapsack[capacity][ITEM]=Knapsack[capacity][ITEM-1];
							
					}
						
				}
				for(int i=0;i<=CAP;i++)
				{
					System.out.println(i+" "+Arrays.toString(Knapsack[i]));
				}
				
				System.out.println(Knapsack[CAP][no]);
			}
	
	}
	public static int MAX(int num1,int num2)
	{
		if(num1>num2)
			return num1;
		return num2;
	}
}
		