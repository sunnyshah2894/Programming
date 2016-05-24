/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *?
 * ?@author Shreyas Zagade
 */
public class SocketClient {
    
    public static void main(String arg[])throws IOException
    {   
        BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
        try {
            Socket socket=new Socket("localhost", 6553);
            Thread.sleep(1000);
            System.out.println("Enter Your Name: ");
            String sending_to_server = brin.readLine();
            PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
            printWriter.println(sending_to_server);
            Thread.sleep(5000);
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(br.readLine());
            Thread.sleep(1000);
            socket.close();
        } catch (Exception ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
