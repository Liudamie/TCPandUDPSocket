package com.qqserver.service;

import com.qqcommon.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static void addClientThread(String userId, ServerConnectClientThread s){
     hm.put(userId, s);
    }
    public static ServerConnectClientThread getServerConnectClientThread(String userId){
        return hm.get(userId);
    }
    public static String getOnlineUser(){
        //集合遍历
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUserList ="";
        while(iterator.hasNext()){
            onlineUserList+= iterator.next()+" ";
        }
        return onlineUserList;

    }
    public static void removeServerConnectClientThread(String userId){
        hm.remove(userId);
    }
    public static HashMap<String, ServerConnectClientThread> getHm(){
        return hm;
    }
}
