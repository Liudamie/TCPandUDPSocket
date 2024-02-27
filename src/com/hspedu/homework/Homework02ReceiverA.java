package com.hspedu.homework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class Homework02ReceiverA {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);

        byte[] buf = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        System.out.println("接收端准备接受");

        socket.receive(packet);

        int length = packet.getLength();

        byte[] data = packet.getData();

        String s = new String(data, 0 ,length);

        String answer = "";
        if("四大名著是什么".equals(s)){
            answer = "四大名著是...";
        }else{
            answer = "what?";
        }

        data = answer.getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.188"), 9998);
        socket.send(datagramPacket);

        socket.close();

        Map<Integer, Integer> map = new HashMap<>();


    }
}
