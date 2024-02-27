package com.hspedu.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class API_ {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost); //DESKTOP-0OFG2KI /192.168.1.188

        InetAddress host1 = InetAddress.getByName("DESKTOP-0OFG2KI");
        System.out.println("host1=" + host1);

        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2+" + host2);

        String hostAddress = host2.getHostAddress();
        System.out.println("host2 ip =" + hostAddress);

        String host2Name = host2.getHostName();
        System.out.println("host2= " + host2Name);

    }
}
