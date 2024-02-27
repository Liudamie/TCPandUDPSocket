package com.hspedu.udp;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);

        byte[] data = "hello, we can go to park tomorrow".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.188"), 9999);


        socket.send(packet);

        byte [] data1 = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(data1, data1.length);


        socket.receive(packet1);
        int length = packet1.getLength();
        byte[] data2 = packet1.getData();
        String s = new String(data2, 0 , length);
        System.out.println(s);
        socket.close();
    }
}
