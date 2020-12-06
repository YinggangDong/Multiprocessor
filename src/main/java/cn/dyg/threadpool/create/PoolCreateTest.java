package cn.dyg.threadpool.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * PoolCreateTest 类是 线程池创建Test
 *
 * @author dongyinggang
 * @date 2020-07-11 14:18
 **/
public class PoolCreateTest {

    public static void main(String[] args) throws InterruptedException {
        cachedThreadPoolTest();
    }

    /**
     * Executors提供了四种默认的线程池创建方法
     * 1.创建一个缓存线程池cachedThreadPool
     */
    public static void cachedThreadPoolTest() throws InterruptedException{
        //创建一个缓存线程池cachedThreadPool
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            cachedThreadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + " is running"));
        }
    }
}
