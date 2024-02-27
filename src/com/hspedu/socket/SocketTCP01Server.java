package com.hspedu.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("服务器监听在9999端口");

        Socket socket = serverSocket.accept();

        System.out.println("服务端 socket = " + socket.getClass());

        InputStream inputStream = socket.getInputStream();

        byte[] buf = new byte[1024];
        int readLen = 0;
        readLen = inputStream.read(buf);
        System.out.println(readLen);
        System.out.println(new String(buf, 0 ,readLen+1));
        while((readLen = inputStream.read(buf))!=-1){
            System.out.println(readLen);
            System.out.println(new String(buf, 0 ,readLen+1));
        }

        inputStream.close();
        socket.close();
        serverSocket.close();


    }
}
