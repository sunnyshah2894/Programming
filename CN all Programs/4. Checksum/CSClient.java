import java.io.*;
import java.util.*;
import java.net.*;

class CSClient
{
    public static void main(String args[])throws Exception
    {

    	BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));

    	int n;
    	System.out.print("total numbers: ");
    	n = Integer.parseInt(inFromUser.readLine());

    	int i, sum = 0;
    	byte A[] = new byte[n+1];
    	for(i=0;i<n;i++)
    	{
        	System.out.print("Number " + (i+1) + " : ");
        	A[i] =(byte)(Integer.parseInt(inFromUser.readLine()));
        	sum += A[i];
    	}

    	A[n] = 0;
    	sum += A[n];
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

    	System.out.print("\nSum in Binary : "+Arrays.toString(rev));

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
		System.out.print("\nlower: " + lower);
		System.out.print("\nwrap: " + wrap);
	    System.out.print("\ntotal: " + cs);
		int revchecksum[] = new int[4];
	    while(no != 0)
	    {
	        revchecksum[i] = no % 2;
	        no = no / 2;
	        i++;
	    }
    	int checksum[] = new int[4];

    	for(i = 0; i < 4; i++)
    	{
        	checksum[i] = revchecksum[4-i-1];
    	}

		System.out.print("\nWrap Sum : "+Arrays.toString(checksum));
	    for(i = 0; i < 4; i++)
	    {
	        if(checksum[i] == 1)
	            checksum[i] = 0;
	        else
	            checksum[i] = 1;
	    }

		System.out.print("\nChecksum in binary : "+Arrays.toString(checksum));
	    int temp = 0;
	    for(i = 0; i < 4; i++)
	    {
	        temp += checksum[i] * Math.pow(2, i);
	    }
		System.out.print("\nChecksum : " + temp);
		A[n] =(byte) temp;


	    System.out.print("\nSending array : " +Arrays.toString(A));


	    Socket clientSocket=new Socket("localhost",2333);
	    DataOutputStream outToServer=new DataOutputStream(clientSocket.getOutputStream());
	    BufferedReader inFromServer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    outToServer.write(n+1);
	    outToServer.write(A,0,n+1);

	    clientSocket.close();
    }
}

/*
Output :
total numbers: 5
Number 1 : 7
Number 2 : 11
Number 3 : 12
Number 4 : 0
Number 5 : 6

Sum in Binary : [1, 0, 0, 1, 0, 0]
lower: 4
wrap: 2
total: 6
Wrap Sum : [0, 1, 1, 0]
Checksum in binary : [1, 0, 0, 1]
Checksum : 9
Sending array : [7, 11, 12, 0, 6, 9]
Process completed.
*/