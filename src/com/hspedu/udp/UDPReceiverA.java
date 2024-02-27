package com.hspedu.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9999);

        byte [] buffer = new byte[64*1024];

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        int length = packet.getLength();

        byte[] data = packet.getData();

        String s = new String(data, 0, length);

        System.out.println(s);

        byte[] data1 = "ok, no problem".getBytes();

        DatagramPacket pa = new DatagramPacket(data1, data1.length, InetAddress.getByName("192.168.1.188"), 9998);

        socket.send(pa);

        socket.close();


    }
}
