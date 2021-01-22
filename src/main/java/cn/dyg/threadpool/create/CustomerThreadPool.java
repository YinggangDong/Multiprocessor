package cn.dyg.threadpool.create;

import cn.dyg.threadpool.factory.MyFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CustomerThreadPool 类是 自定义线程池
 *
 * @author dongyinggang
 * @date 2021-01-20 14:55
 **/
public class CustomerThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService customThreadPool = customThreadPool();
        Future future = customThreadPool.submit(()->
            System.out.println(Thread.currentThread().getName()));
        System.out.println(future.get());
    }

    /**
     * customThreadPool 方法是 创建一个自定义线程池
     *
     * @return 自定义线程池
     * @author dongyinggang
     * @date 2021/1/20 15:56
     */
    public static ExecutorService customThreadPool(){
        //核心线程数
        int corePoolSize = 10;
        //最大线程数
        int maximumPoolSize = 10;
        //非核心线程的最长存活时间（非核心线程多长时间后未执行任务就进行销毁）
        long keepAliveTime = 0;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        String poolNamePrefix = "自定义线程池";
        String threadNamePrefix = "线程名称前缀";
        ThreadFactory threadFactory = new MyFactory(poolNamePrefix,threadNamePrefix);
        ExecutorService customThreadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,
                keepAliveTime,TimeUnit.SECONDS,workQueue,threadFactory);

        return customThreadPool;
    }

}
