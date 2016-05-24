/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Shreyas Zagade
 */
public class SocketServer {
    
    public static void main(String arg[]) throws IOException, InterruptedException
    {
        
        ServerSocket serverSocket=new ServerSocket(6553);
        System.out.println("SocketServer");
        while(true){
        Socket socket=serverSocket.accept();
        System.out.println("Accept");
        Thread.sleep(1000);
        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message=br.readLine();
        System.out.println("Message:"+message);
        Thread.sleep(1000);
        PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
        if(message.equalsIgnoreCase("exit"))
            break;
        Thread.sleep(1000);
        printWriter.println("Server's Reply: Hi "+message);
        socket.close();
        }
        serverSocket.close();
    }
    
}
