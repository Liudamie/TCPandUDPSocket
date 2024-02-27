package com.hspedu.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFIleUploadServer {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new  ServerSocket(8888);

        System.out.println("服务端在8888端口监听");

        Socket socket = serverSocket.accept();

        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

        byte [] bytes = StreamUtils.streamToByteArray(bis);



        String destFilePath = "src\\qie2.png";

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));

        bos.write(bytes);
        bos.close();




        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("收到图片");
        bufferedWriter.flush();
        socket.shutdownOutput();

        bufferedWriter.close();
        bis.close();
        socket.close();
        
        serverSocket.close();


    }
}
