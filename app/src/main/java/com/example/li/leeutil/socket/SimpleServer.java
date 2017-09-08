package com.example.li.leeutil.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Li on 2017/9/8.
 */

public class SimpleServer {
    public static void main(String[] agrs){
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("server setup succeed...");

            while (true){
                Socket socket = serverSocket.accept();


                OutputStream os = socket.getOutputStream();
                os.write("你好，你收到了来自服务器的消息".getBytes("utf-8"));
                os.close();


                socket.close();
            }
        } catch (IOException e) {
            System.out.println("maybe server has been setup...");
//            e.printStackTrace();
        }

    }
}
