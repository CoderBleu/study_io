package bio.false_ansyc;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lauy
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("----------服务端启动成功------------");
            ServerSocket ss = new ServerSocket(8888);

            // 一个服务端只需要对应一个线程池
            HandlerSocketThreadPool handlerSocketThreadPool = new HandlerSocketThreadPool();

            // 客户端可能有很多个
            while (true) {
                // 阻塞式的！只是为每一个连接分配了线程
                Socket socket = ss.accept();
                System.out.println("有人上线了！！");
                // 每次收到一个客户端的socket请求，都需要为这个客户端分配一个
                // 独立的线程 专门负责对这个客户端的通信！！
                handlerSocketThreadPool.execute(new ReaderClientRunnable(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
