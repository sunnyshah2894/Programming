import java.util.Scanner;
import java.util.Arrays;
class Banker
{
	public static void main(String ar[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter no.of processes:");
		int pno=s.nextInt();
		System.out.println("Enter no. of resources:");
		int rno=s.nextInt();
		int claim[][]=new int[pno][rno];
		int allo[][]=new int[pno][rno];
		int need[][]=new int[pno][rno];
		int res[]=new int[rno];
		int avail[]=new int[rno];
		System.out.println("Enter claim matrix:");
		for(int i=0;i<pno;i++)
			for(int j=0;j<rno;j++)
				claim[i][j]=s.nextInt();
		System.out.println("Enter allocated matrix:");
		for(int i=0;i<pno;i++)
			for(int j=0;j<rno;j++)
				allo[i][j]=s.nextInt();
		System.out.println("Enter max resources:");
		for(int i=0;i<rno;i++)
			res[i]=s.nextInt();
		for(int i=0;i<pno;i++)
			for(int j=0;j<rno;j++)
				need[i][j]=claim[i][j]-allo[i][j];
		for(int i=0;i<rno;i++)
		{
			int sum=0;
			for(int j=0;j<pno;j++)
				sum+=allo[j][i];
			avail[i]=res[i]-sum;
		}
		System.out.println("Available resources:"+Arrays.toString(avail));
		boolean done[]=new boolean[pno];
		int ctr=0;
		boolean exec=false;
		while(ctr!=pno)
		{
			boolean safe=false;
			for(int i=0;i<pno;i++)
			{
				if(!done[i])
				{
				    exec=true;
					for(int j=0;j<rno;j++)
						if(need[i][j]>avail[j])
							exec=false;
					if(exec)
					{
						ctr++;
						System.out.println("Process "+(i+1)+" executed");
						safe=true;
						done[i]=true;
						for(int j=0;j<rno;j++)
						{
							avail[j]-=need[i][j];
							need[i][j]=0;
							avail[j]+=claim[i][j];
						}
					System.out.println("Available resources:"+Arrays.toString(avail));
					}
				}
			}
			if(!safe)
			{
				System.out.println("Processes are in unsafe state");
				break;
			}
		}
	}
}