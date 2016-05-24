import java.util.*;

class encrypt
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String s;
		System.out.print("enter the string : ");
		s=sc.next();
		System.out.print("ASCII ORIGINAL");
		System.out.println("        ASCII ENCRYPT");
		int p=0;
		String e="";
		String t=" ";
		int a=7;
		int b=13;
		for(int i=0;i<s.length();i++)
		{
			p=s.charAt(i)-65;
			System.out.print(p+"\t\t\t\t\t\t");
			int	c=(a*p+b)%26;
			int z=(a*p+b)/26;
			p=((z*26+c)-b)/a;
			System.out.println(c);
			e+=(char)(c+65);
			t+=(char)(p+65);
		}
	System.out.print("\nTHE ENCRYPTED STRING :"+e);
	System.out.print("\nTHE DECRYPTED STRING :"+t);
	}
}

/*
Output :

enter the string : ashish
ASCII ORIGINAL        ASCII ENCRYPT
32						3
50						25
39						0
40						7
50						25
39						0

THE ENCRYPTED STRING :DZAHZA
THE DECRYPTED STRING : ashish
Process completed.
*/