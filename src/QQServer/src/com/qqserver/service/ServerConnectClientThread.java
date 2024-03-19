package com.qqserver.service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public void run(){
        while (true){
            try {
                System.out.println("服务端和客户端保持通信，读取数据");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                switch (message.getMesType()){
                    case MessageType.MESSAGE_GET_ONLINE_FRIEND :
                        System.out.println(message.getSender()+"要在线用户列表");
                        String onlineUser = ManageClientThreads.getOnlineUser();
                        Message message2 = new Message();
                        message2.setMesType(MessageType.MESSAGE_RETURN_GET_ONLINE_FRIEND);
                        message2.setContent(onlineUser);
                        message2.setGetter(message.getSender());
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(message2);
                        break;
                    case MessageType.MESSAGE_CLIENT_EXIT:
                        System.out.println(message.getSender()+ "退出");
                        Message message3 = new Message();
                        message3.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
                        message3.setGetter(message.getSender());
                        ObjectOutputStream oosEixt = new ObjectOutputStream(socket.getOutputStream());
                        oosEixt.writeObject(message3);
                        ois.close();
                        ManageClientThreads.removeServerConnectClientThread(message.getSender());
                        socket.close();
                        break;
                    case MessageType.MESSAGE_COMM_MES:
                        ServerConnectClientThread serverConnectClientThread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                        ObjectOutputStream oos1 = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                        oos1.writeObject(message);
                        break;

                    case MessageType.MESSAGE_TO_ALL_MES:
                        HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
                        Iterator it = hm.keySet().iterator();
                        while (it.hasNext()){
                            String onLineUserId = it.next().toString();
                            if(!onLineUserId.equals(message.getSender())){
                                ObjectOutputStream oos2 = new ObjectOutputStream(hm.get(onLineUserId).getSocket().getOutputStream());
                                oos2.writeObject(message);
                            }
                        }
                        break;
                    case MessageType.MESSAGE_FILE_MES:
                        ServerConnectClientThread serverConnectClientThread1 = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                        ObjectOutputStream oos2 = new ObjectOutputStream(serverConnectClientThread1.getSocket().getOutputStream());
                        oos2.writeObject(message);
                        break;


                }
                if(message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) break;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
