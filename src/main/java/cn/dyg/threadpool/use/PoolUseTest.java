package cn.dyg.threadpool.use;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PoolUseTest 类是 测试线程池的使用
 *
 * @author dongyinggang
 * @date 2020-11-25 13:58
 **/
public class PoolUseTest {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(1);
        ExecutorService executorService = new ThreadPoolExecutor(10,
                100,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                (r) -> new Thread(r, "test-t" + counter.getAndIncrement())
        );

        for (int i = 0; i < 10; i++) {

            executorService.execute(() ->
                    System.out.println(Thread.currentThread().getName() + "运行"));
        }
        executorService.shutdown();
    }

}
