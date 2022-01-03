package bio.multiple;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author lauy
 * @date 2021/12/12
 * @description 多发客户端
 */
public class MultipleClient {

    public static void main(String[] args) {
        // 1.创建Socket对象请求服务端的链接
        try (Socket socket = new Socket("127.0.0.1", 8888)) {
            // 2、从Socket对象中获取一个字节输出流
            OutputStream os = socket.getOutputStream();
            // 3、把字节输出流包装成一个打印流
            PrintStream ps = new PrintStream(os);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入：");
                String input = scanner.nextLine();
                ps.println(input);
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
