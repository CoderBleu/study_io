package bio.multiple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lauy
 * @date 2021/12/12
 * @description
 */
public class MultipleServer {
    public static void main(String[] args) {
        System.out.println("====Server starting====");
        try {
            // 1、定一一个ServerSocket对象进行服务端的端口注册
            ServerSocket serverSocket = new ServerSocket(8888);
            //  2、监听客户端的Socket链接请求
            Socket accept = serverSocket.accept();
            // 3、从Socket冠道中得到一个字节输入流对象
            InputStream is = accept.getInputStream();
            // 4、把字节输入流包装成一个缓冲字符的输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receive;
            while ((receive = br.readLine()) != null) {
                System.out.println("服务端接收到：" + receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
