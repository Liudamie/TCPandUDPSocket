package com.qqserver.service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class SendNewsToAllService implements Runnable{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (true){
            System.out.println("请输入服务器要推送的新闻/消息");
            String news = scanner.next();
            Message message = new Message();
            message.setSender("服务器");
            message.setContent(news);
            message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
            message.setSendTime(new Date().toString());
            System.out.println("服务区推送消息给所有人 说："+ news);
            Iterator it = ManageClientThreads.getHm().keySet().iterator();
            while(it.hasNext()){
                String userId = it.next().toString();
                ServerConnectClientThread serverConnectClientThread = ManageClientThreads.getServerConnectClientThread(userId);
                Socket socket = serverConnectClientThread.getSocket();
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
