package bio.multiple_connections.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lauy
 * @date 2021/12/12
 * @description
 * 目标：实现服务端可以同时接收多个客户端的Socket通信需求
 * 思路：是服务端每接收到一个客户端Socket请求对象之后都交给一个独立的线程执行
 */
public class MultipleThreadServer {
    public static void main(String[] args) {
        System.out.println("====Multiple thread server starting====");
        try {
            // 1、定一一个ServerSocket对象进行服务端的端口注册
            ServerSocket serverSocket = new ServerSocket(8888);
            // 2、定义一个死循环，负责不断的接受客户端的Socket连接请求
            while (true) {
                //  2、监听客户端的Socket链接请求
                Socket socket = serverSocket.accept();
                new ServerThreadReader(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
