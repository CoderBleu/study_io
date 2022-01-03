package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lauy
 * @date 2021/12/27
 * @description
 */
public class ChannelDemo {

    private static final String FILE_PATH = "/Users/lauy/coding/java/study_bio/src/main/resources/channel_01.txt";

    private static final String JPG_PATH = "/Users/lauy/coding/java/study_bio/src/main/resources/file.jpg";

    private static final String JPG_PREFIX = "/Users/lauy/coding/java/study_bio/src/main/resources/%s";

    public static void main(String[] args) throws Exception {
        //writeDataToFile();
        //readDataFromFile();
        copyFile();
    }

    private static void writeDataToFile() {
        // 1、字节输出流通向目标文件
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(FILE_PATH);
            // 2、得到字节输出流对应的通道Channel
            FileChannel channel = fos.getChannel();
            // 3、分配缓冲区，最多1kb数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello,我的耳鸣什么时候好呢！".getBytes());
            // 4、把缓冲区切换成写出模式
            buffer.flip();
            channel.write(buffer);
            channel.close();
            System.out.println("写数据到文件中！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDataFromFile() {
        try {
            // 1、定义一个文件字节输入流与源文件接通
            FileInputStream is = new FileInputStream(FILE_PATH);
            // 2、需要得到文件字节输入流的文件通道
            FileChannel channel = is.getChannel();
            // 3、定义一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 4、读取数据到缓冲区
            channel.read(buffer);
            // 把缓冲区的模式切换成可读模式
            buffer.flip();
            // 5、读取出缓冲区中的数据并输出即可
            String rs = new String(buffer.array(), 0, buffer.remaining());
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile() {
        try {
            // 源文件
            File srcFile = new File(JPG_PATH);
            File destFile = new File(String.format(JPG_PREFIX, "copyfile.jpg"));
            // 得到一个字节字节输入流
            FileInputStream fis = new FileInputStream(srcFile);
            // 得到一个字节输出流
            FileOutputStream fos = new FileOutputStream(destFile);
            // 得到的是文件通道
            FileChannel isChannel = fis.getChannel();
            FileChannel osChannel = fos.getChannel();
            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(true){
                // 必须先清空缓冲然后再写入数据到缓冲区
                buffer.clear();
                // 开始读取一次数据
                int flag = isChannel.read(buffer);
                if(flag == -1){
                    break;
                }
                // 已经读取了数据 ，把缓冲区的模式切换成可读模式
                buffer.flip();
                // 把数据写出到
                osChannel.write(buffer);
            }
            isChannel.close();
            osChannel.close();
            System.out.println("复制完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
