import java.net.*;
import java.io.*;

class HammingClient
{
    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Dataword : ");
        String dataword = br.readLine();
        String codeword = "";

        char r0, r1, r2;
        char a0 = dataword.charAt(3);
        char a1 = dataword.charAt(2);
        char a2 = dataword.charAt(1);
        char a3 = dataword.charAt(0);

        r2 = xor(a0, a1, a3);
        r1 = xor(a1, a2, a3);
        r0 = xor(a0, a1, a2);

        codeword = dataword + r2 + r1 + r0;
        System.out.print("Codeword : " + codeword);

        Socket s = new Socket("localhost", 1254);
        OutputStream out = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(codeword);

        dos.close();
        out.close();
        s.close();
    }

    public static char xor(char c1, char c2, char c3)
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

        return r;
    }
}