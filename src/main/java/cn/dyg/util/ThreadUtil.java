package cn.dyg.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadUtil 类是 线程工具类
 * 1.创建线程池
 *
 * @author dongyinggang
 * @date 2020-11-26 11:29
 **/
public class ThreadUtil {

    public static ExecutorService buildThreadExecutor(int threadSize) {
        int keepAlive = 0;
        String prefix = "test";
        ThreadFactory factory = ThreadUtil.buildThreadFactory(prefix);
        return new ThreadPoolExecutor(threadSize,
                threadSize,
                keepAlive,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                factory);

    }

    public static ThreadFactory buildThreadFactory(String prefix) {
        return new SelfThreadFactory(prefix);
    }

    public static class SelfThreadFactory implements ThreadFactory {

        //线程名称前缀
        private String threadNamePrefix;
        //原子性Integer对象生成器
        private AtomicInteger counter = new AtomicInteger(1);

        SelfThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }


        /**
         * newThread 方法是 生成线程
         *
         * @param r runnable接口
         * @return 线程
         * @author dongyinggang
         * @date 2020/11/26 11:43
         */
        @Override
        public Thread newThread(Runnable r) {
            //获取线程名称
            String threadName = threadNamePrefix + "-t" + counter.getAndIncrement();
            return new Thread(r, threadName);
        }
    }


}
