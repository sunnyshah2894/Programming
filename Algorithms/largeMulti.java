import java.io.IOException;
import java.util.Scanner;


public class largeMulti {
	public static void main(String[] args)throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter The 1st Number: ");
		String one=sc.nextLine();
		System.out.println("Enter The 2nd Number: ");
		String two=sc.nextLine();
		String prod=multi(one,two);
		System.out.println(prod);
	}

	static String multi(String one, String two) {
		if(one.length()>1 && two.length()>1){
			
			int mid1=one.length()/2;
			int mid2=two.length()/2;
			String a=one.substring(0, mid1);
			String b=one.substring(mid1, one.length());
			String c=two.substring(0, mid2);
			String d=two.substring(mid2, two.length());
			
			String ac=multi(a,c);
			String ad=multi(a,d);
			String bc=multi(b,c);
			String bd=multi(b,d);
			
			return (Long.parseLong(ac)*(long)Math.pow(10,b.length()+d.length()) + Long.parseLong(ad)*(long)Math.pow(10, b.length()) + Long.parseLong(bc)*(long)Math.pow(10,d.length()) + Long.parseLong(bd))+"";
			
		}
		else{
			return (Integer.parseInt(one)*Integer.parseInt(two))+"";
		}
	}
}
