package nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author lauy
 * @date 2021/12/12
 * @description
 */
public class TransferFromDemo {

    private static final String FILE_PATH_PREFIX = System.getProperty("user.dir") + "/study_nio/src/main/resources";

    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile(FILE_PATH_PREFIX + "/channel_01.txt", "rw");
            FileChannel fromChannel = aFile.getChannel();

            RandomAccessFile bFile = new RandomAccessFile(FILE_PATH_PREFIX + "/channel_from.txt", "rw");
            FileChannel toChannel = bFile.getChannel();

            long position = 0L;
            long size = fromChannel.size();
            fromChannel.transferFrom(fromChannel, position, size);

            aFile.close();
            bFile.close();
            System.out.println("Over!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
