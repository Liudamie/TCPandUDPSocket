package com.hspedu.homework;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Homework02SenderB {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("请输入你的问题");

        String question = scanner.next();

        byte[] data = question.getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.188"), 8888);

        socket.send(datagramPacket);

        byte [] buf = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        socket.receive(packet);

        int length = packet.getLength();
         data = packet.getData();
        String s = new String(data,0,length);

        System.out.println(s);
        socket.close();

    }
}
