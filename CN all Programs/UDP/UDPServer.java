/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CN;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Shreyas Zagade
 */
public class UDPServer {
    
    public static void main(String arg[]) throws SocketException, IOException
    {
        DatagramSocket serverSocket=new DatagramSocket(9876);
        byte receiveData[]=new byte[1024];
        byte sendData[]=new byte[1024];
        while(true)
        {
            DatagramPacket datagramPacket=new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(datagramPacket);
            String msg=new String(datagramPacket.getData());
            System.out.println(msg);
            InetAddress ipAddress=datagramPacket.getAddress();
            int port=datagramPacket.getPort();
            msg=msg.toUpperCase();
            sendData=msg.getBytes();
            DatagramPacket sendPacket=new DatagramPacket(sendData, sendData.length, ipAddress, port);
            serverSocket.send(sendPacket);
        }
    }
    
}
