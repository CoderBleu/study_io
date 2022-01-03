package bio.single;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author lauy
 * @date 2021/12/12
 * @description 客户端
 */
public class Client {

    public static void main(String[] args) {
        // 1.创建Socket对象请求服务端的链接
        try (Socket socket = new Socket("127.0.0.1", 8888)) {
            // 2、从Socket对象中获取一个字节输出流
            OutputStream os = socket.getOutputStream();
            // 3、把字节输出流包装成一个打印流
            PrintStream ps = new PrintStream(os);
            ps.println("hello world! 服务端，您好！");
            ps.flush();
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
