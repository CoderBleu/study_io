package bio.false_ansyc;

import java.util.concurrent.*;

/**
 * @author lauy
 * @description 线程池处理类
 */
public class HandlerSocketThreadPool {

    /**
     * 线程池
     */
    private ExecutorService executor;

    public HandlerSocketThreadPool() {
        this.executor = new ThreadPoolExecutor(
                8,
                16,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void execute(Runnable task) {
        this.executor.execute(task);
    }
}
