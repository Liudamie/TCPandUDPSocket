package com.qqclient.view;

import com.qqclient.view.Service.FileClientService;
import com.qqclient.view.Service.MessageClientService;
import com.qqclient.view.Service.UserClientService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class QQView {
    private boolean loop = true;
    private String key = "";

    private UserClientService userClientService = new UserClientService();
    private MessageClientService messageClientService = new MessageClientService();
    public static void main(String[] args) throws IOException {

        new QQView().mainMenu();
        System.out.println("客户端退出系统");
    }
    private void mainMenu() throws IOException {
        while (loop){
            System.out.println("=========欢迎登录网络通信系统=======");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择：");
            Scanner scanner = new Scanner(System.in);
            key = scanner.next();
            switch (key){
                case "1" :
                    System.out.println("请输入用户号：");
                    String userId = scanner.next();
                    System.out.println("请输入密 码：");
                    String pwd = scanner.next();
                    if(userClientService.checkout(userId,pwd)){
                        System.out.println("=========欢迎（用户"+ userId +"）=======");
                        while (loop){
                            System.out.println("\n=========网络通信二级菜单（用户"+ userId +"）=======");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.println("请输入你的选择");
                            key = scanner.next();
                            switch (key){
                                case "1" :
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入相对大家说的话：");
                                    String s = scanner.next();
                                    messageClientService.sendMessageToAll(s, userId);
                                    break;
                                case "3" :
                                    System.out.println("\t\t 请输入想聊天的用户号（在线）：");
                                    String getterId = scanner.next();
                                    System.out.println("请输入想说的话");
                                    String content = scanner.next();
                                    messageClientService.sendMessageToOne(content,userId,getterId);
                                    System.out.println("\t\t 3 私聊消息");
                                    break;
                                case "4" :
                                    System.out.print("请输入你想把文件发送给的在线用户：");
                                    getterId = scanner.next();
                                    System.out.print("请输入发送文件的路径（形式 D;\\xx.jpg）");
                                    String src = scanner.next();
                                    System.out.print("请输入把文件发送到对方的路径 （形式 D:\\xx.jpg）");
                                    String dest = scanner.next();
                                    FileClientService.sendFileToOne(src,dest, userId,getterId);
                                    break;
                                case "9" :
                                    userClientService.logout();
                                    loop = false;
                                    break;

                            }
                        }
                    }else{
                        System.out.println("=========登录失败==========");
                    }
                    break;
                case "9" :
                    loop = false;
                    break;

            }
        }
    }

}
