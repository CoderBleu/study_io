package bio.multiple_connections.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author lauy
 * @date 2021/12/12
 * @description
 */
public class ServerThreadReader extends Thread {

    private Socket socket;

    public ServerThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 3、从Socket冠道中得到一个字节输入流对象
        InputStream is = null;
        try {
            is = socket.getInputStream();
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
