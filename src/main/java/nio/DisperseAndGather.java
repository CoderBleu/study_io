package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lauy
 * @date 2021/12/27
 * @description 分散读取（Scatter ）:是指把Channel通道的数据读入到
 * 多个缓冲区中去
 * <p>
 * 聚集写入（Gathering ）是指将多个 Buffer 中的数
 * 据“聚集”到 Channel
 */
public class DisperseAndGather {

    private static final String FILE_PATH = "/Users/lauy/coding/java/study_bio/src/main/resources/%s";

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile(String.format(FILE_PATH, "channel_01.txt"), "rw");
        //1. 获取通道
        FileChannel channel1 = raf1.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("-----------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //4. 聚集写入
        String gatherFile = String.format(FILE_PATH, "channel_02.txt");
        RandomAccessFile raf2 = new RandomAccessFile(gatherFile, "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);
    }
}
