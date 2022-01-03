package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lauy
 * @date 2021/12/12
 * @description
 */
public class FileChannelDemo {

    public static void main(String[] args) {
        try {
            RandomAccessFile accessFile = new RandomAccessFile(System.getProperty("user.dir") + "/study_nio/src/main/resources" + "/channel_01.txt", "rw");
            FileChannel channel = accessFile.getChannel();

             //创建Buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 读取数据到buffer中
            int read = channel.read(buffer);
            while (read != -1) {
                System.out.println("读取了：" + read);
                //
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char b = (char)buffer.get();
                    System.out.println(b);
                }
                // 清除此缓冲区。将位置设置为零，将限制设置为容量，并丢弃标记。
                buffer.clear();
                read = channel.read(buffer);
            }
            accessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
