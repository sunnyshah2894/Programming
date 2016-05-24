import java.io.*;
import java.util.*;
import java.net.*;

class CSServer
{
    public static void main(String args[])throws IOException
    {

        ServerSocket welcomeSocket=new ServerSocket(2333);
	 	while(true)
	 	{
        	Socket connectionSocket = welcomeSocket.accept();
			InputStream isr = connectionSocket.getInputStream();
        	DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());

			int n = isr.read();
	 		byte A[] = new byte[n];
       		isr.read(A,0,n);
			System.out.print("\nReceived : " + Arrays.toString(A));


			int i, sum = 0;
			for(i = 0; i < n; i++)
	    		sum+= A[i];

			int no = sum;
			int b[] = new int[10];
			i = 0;
			while(no != 0)
			{
				b[i] = no % 2;
				no = no / 2;
				i++;
			}

			int s = i-1;
			int rev[] = new int[i];

			for(i = 0; i <= s; i++)
			{
				rev[i] = b[s-i];
			}
			System.out.print("\nSum in binary : "+Arrays.toString(rev));


			int j;
			int lower = 0;
			for(j = 0; j < 4; j++)
			{
				lower += rev[s-j] * Math.pow(2, j);
			}

			int wrap = 0;
			for(j = 0; j < s-4; j++)
			{
				wrap += rev[j] * Math.pow(2, s-4-j);
			}

			int cs = lower + wrap;
			no = cs;
			i = 0;
			System.out.print("\nlower : " + lower);
			System.out.print("\nwrap : " + wrap);
			System.out.print("\ntotal : " + cs);

			int revchecksum[] = new int[4];
			while(no != 0){

				revchecksum[i] = no % 2;
				no = no / 2;
				i++;
			}
			int checksum[] = new int[4];

			for(i = 0; i < 4; i++){

				checksum[i] = revchecksum[4-i-1];
			}

			System.out.print("\nWrap Sum : "+Arrays.toString(checksum));

			for(i = 0; i < 4; i++){

				if(checksum[i] == 1)
					checksum[i] = 0;
				else
					checksum[i] = 1;
			}


			System.out.print("\nChecksum in binary : "+Arrays.toString(checksum));
			int temp = 0;
			for(i = 0; i < 4; i++){

				temp += checksum[i] * Math.pow(2, i);
			}
			System.out.print("\nChecksum: " + temp);
			if(temp==0)
				System.out.print("\nMessage Received Successfully...");
			else
				System.out.print("\nMessage NOT Received Successfully...");
	 }
    }
}
/*
Output :
Received : [7, 11, 12, 0, 6, 9]
Sum in binary : [1, 0, 1, 1, 0, 1]
lower : 13
wrap : 2
total : 15
Wrap Sum : [1, 1, 1, 1]
Checksum in binary : [0, 0, 0, 0]
Checksum: 0
Message Received Successfully...
*/