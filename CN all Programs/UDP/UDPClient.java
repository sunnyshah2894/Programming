/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Shreyas Zagade
 */
public class UDPClient {
    
    public static void main(String arg[]) throws SocketException, UnknownHostException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket=new DatagramSocket();
        InetAddress ipAddress=InetAddress.getByName("localhost");
        System.out.println(ipAddress);
        byte sendData[]=new byte[1024];
        byte receiveData[]=new byte[1024];
        sendData=br.readLine().getBytes();
        DatagramPacket sendPacket=new DatagramPacket(sendData, sendData.length, ipAddress, 9876);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
       clientSocket.receive(receivePacket);
       String modifiedSentence = new String(receivePacket.getData());
       System.out.println("FROM SERVER:" + modifiedSentence);
       clientSocket.close();
    }
    
}
