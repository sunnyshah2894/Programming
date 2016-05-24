import java.net.*;
import java.io.*;

class HammingServer
{
    public static void main(String args[])throws Exception
    {
        ServerSocket ss = new ServerSocket(1254);
        Socket s = ss.accept();
        InputStream ins = s.getInputStream();
        DataInputStream dis = new DataInputStream(ins);

        String codeword = dis.readUTF();
        //codeword = "0011001";
        System.out.println("Received Codeword: " + codeword);

        char q0 = codeword.charAt(6);
        char q1 = codeword.charAt(5);
        char q2 = codeword.charAt(4);
        char b0 = codeword.charAt(3);
        char b1 = codeword.charAt(2);
        char b2 = codeword.charAt(1);
        char b3 = codeword.charAt(0);

        char s0 = xor(b2, b1, b0, q0);
        char s1 = xor(b3, b2, b1, q1);
        char s2 = xor(b1, b0, b3, q2);

        int  err = 0;
        char c[] = codeword.toCharArray();

        if(s2 == '0' && s1 == '0' && s0 == '0')
            System.out.println("No Error :)");

        else
        {
            if(s2 == '0' && s1 == '0' && s0 == '1')
            {
	            System.out.println("Error at bit qo");
	            err = 6;
        	}
        else if(s2 == '0' && s1 == '1' && s0 == '0')
        	{
	            System.out.println("Error at bit q1");
	            err = 5;
        	}
        else if(s2 == '0' && s1 == '1' && s0 == '1')
        	{
	            System.out.println("Error at bit b2");
	            err = 1;
        	}
        else if(s2 == '1' && s1 == '0' && s0 == '0')
        	{
	            System.out.println("Error at bit q2");
	            err = 4;
        	}
        else if(s2 == '1' && s1 == '0' && s0 == '1')
        	{
	            System.out.println("Error at bit b0");
	            err = 3;
	        }
        else if(s2 == '1' && s1 == '1' && s0 == '0')
        	{
	            System.out.println("Error at bit b3");
	            err = 0;
	        }
        else if(s2 == '1' && s1 == '1' && s0 == '1')
        	{
	            System.out.println("Error at bit b1");
	            err = 2;
	        }

            if(c[err] == '0')
                c[err] = '1';
            else
                c[err] = '0';
        }

        System.out.println("Correct codeword : " + new String(c));
    }

    public static char xor(char c1, char c2, char c3, char c4)
    {
        char r;
        if(c1 == c2)
            r = '0';
        else
            r = '1';

        if(c3 == r)
            r = '0';
        else
            r = '1';

        if(c4 == r)
            r = '0';
        else
            r = '1';

        return r;
    }
}