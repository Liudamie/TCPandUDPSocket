package com.hspedu.upload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPFileUploadClient {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(),8888);


        String filePath = "C:\\Users\\DELL\\Documents\\WeChat Files\\wxid_csyr6vo6yatn22\\FileStorage\\Video\\2023-11\\input.jpg";

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        byte[] bytes = StreamUtils.streamToByteArray(bis);
        bis.close();

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());

        bufferedOutputStream.write(bytes);



        socket.shutdownOutput();


        InputStream inputStream = socket.getInputStream();

        String s = StreamUtils.streamToString(inputStream);
        bufferedOutputStream.close();
        System.out.println(s);

        int [] a = new int[2];
        int x = a.length;

        inputStream.close();

        socket.close();






    }
}
