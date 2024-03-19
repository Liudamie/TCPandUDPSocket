package com.qqclient.view.Service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread{

    private Socket socket;
    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message ms = (Message) ois.readObject();
                if(ms.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    ois.close();
                    socket.close();
                    System.exit(0);
                    break;
                }

                switch (ms.getMesType()){
                    case MessageType.MESSAGE_RETURN_GET_ONLINE_FRIEND :
                        System.out.println("\n======当前在线用户列表======");
                        String[] onlineUsers = ms.getContent().split(" ");
                        for(int i=0;i<onlineUsers.length;i++){
                            System.out.println("用户："+ onlineUsers[i]);
                        }
                        break;
                    case MessageType.MESSAGE_COMM_MES:
                        System.out.println("\n" +ms.getSender()+"对"+ms.getGetter()+
                                "说"+ms.getContent());
                        break;
                    case MessageType.MESSAGE_TO_ALL_MES:
                        System.out.println("\n"+ms.getSender()+"对所有人说"+ ms.getContent());
                        break;
                    case MessageType.MESSAGE_FILE_MES:
                        FileOutputStream fileOutputStream = new FileOutputStream(ms.getDest());
                        fileOutputStream.write(ms.getFileBytes());
                        fileOutputStream.close();
                        break;




                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }
}
