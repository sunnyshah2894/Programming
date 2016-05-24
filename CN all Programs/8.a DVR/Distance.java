import java.util.*;

class Distance
{
	public static void main(String args[])
	{
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter the no. of nodes : ");
			int nodes=sc.nextInt();
			System.out.print("\nEnter the source node : ");
			String source=sc.next();
			System.out.print("\nEnter the destination node : ");
			String des=sc.next();
			String a[][]=new String[nodes][2];
			String b[][]=new String[nodes][2];
			System.out.print("\nEnter the Source node "+source+" "+"values :\n");
			for(int i=0;i<nodes;i++)
			{
				for(int j=0;j<2;j++)
					a[i][j]=sc.next();
			}
			System.out.print("\nDo you want to add delay from "+source+" (enter yes/no) : ");
			String choice=sc.next();
			if(choice.equals("yes"))
			{
				System.out.print("Add delay from source to middle node : ");
				int delay=sc.nextInt();
				for(int i=0;i<nodes;i++)
				{
					for(int j=1;j<2;j++)
					{
						int m=Integer.parseInt(a[i][j]);
						m+=delay;
						a[i][j]=m+"";
					}
				}
			}
			System.out.print("\nEnter the destination node "+des+" values :\n");
			for(int i=0;i<nodes;i++)
			{
				for(int j=0;j<2;j++)
					b[i][j]=sc.next();
			}
			System.out.print("\nDo you want to add delay from "+source+" (enter yes/no) : ");
			choice=sc.next();
			if(choice.equals("yes"))
			{
				System.out.print("Add delay from destination to middle node : ");
				int delay=sc.nextInt();
				for(int i=0;i<nodes;i++)
				{
					for(int j=1;j<2;j++)
					{
						int m=Integer.parseInt(b[i][j]);
						m+=delay;
						b[i][j]=m+"";
					}
				}
			}
			String c[][]=new String[nodes][2];
			for(int i=0;i<nodes;i++)
				c[i][0]=b[i][0];
			for(int i=0;i<nodes;i++)
			{
				for(int j=1;j<2;j++)
				{
					int m=Integer.parseInt(b[i][j]);
					int n=Integer.parseInt(a[i][j]);
					if(m<n)
						c[i][j]=m+" "+des;
					else
						c[i][j]=n+" "+source;
				}
			}
			System.out.print("\nThe new minimum table is :\n");
			for(int i=0;i<nodes;i++)
			{
				for(int j=1;j<2;j++)
				System.out.print(c[i][j]+"\t\t");
				System.out.println();
			}
			System.out.print("\nDo you want to continue? (Enter yes/no) : ");
			String sel=sc.next();
			if(sel.equals("no"))
				break;
		}
	}
}

/*
Output :
Enter the no. of nodes : 9

Enter the source node : A

Enter the destination node : H

Enter the Source node A values :
A 0
B 16
C 17
D 26
E 13
F 18
G 12
H 19
I 0

Do you want to add delay from A (enter yes/no) : yes
Add delay from source to middle node : 8

Enter the destination node H values :
A 17
B 24
C 31
D 15
E 10
F 6
G 19
H 0
I 18

Do you want to add delay from A (enter yes/no) : yes
Add delay from destination to middle node : 12

The new minimum table is :
8 A
24 A
25 A
27 H
21 A
18 H
20 A
12 H
8 A

Do you want to continue? (Enter yes/no) : no

Process completed.

*/
