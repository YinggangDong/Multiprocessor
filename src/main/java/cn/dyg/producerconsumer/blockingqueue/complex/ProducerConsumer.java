package cn.dyg.producerconsumer.blockingqueue.complex;

import cn.dyg.threadpool.factory.MyFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ProducerConsumer 类是 生产着消费者模式启动类
 *
 * @author dongyinggang
 * @date 2020-06-28 21:43
 **/
public class ProducerConsumer {

    private static final String PRODUCER_POOL_NAME = "[生产者线程池]";
    private static final String PRODUCER_THREAD_NAME = "[生产者线程]";
    private static final String CONSUMER_POOL_NAME = "[消费者线程池]";
    private static final String CONSUMER_THREAD_NAME = "[消费者线程]";

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor producerExecutor = new ThreadPoolExecutor(4, 8,
                2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),
                new MyFactory(PRODUCER_POOL_NAME, PRODUCER_THREAD_NAME));
        ThreadPoolExecutor consumerExecutor = new ThreadPoolExecutor(4, 8,
                2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),
                new MyFactory(CONSUMER_POOL_NAME, CONSUMER_THREAD_NAME));
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        //新建线程
        ProducerThread producerThread1 = new ProducerThread(queue);
        ProducerThread producerThread2 = new ProducerThread(queue);
        ProducerThread producerThread3 = new ProducerThread(queue);
        ProducerThread producerThread4 = new ProducerThread(queue);
        //线程池加载线程
        producerExecutor.execute(producerThread1);
        producerExecutor.execute(producerThread2);
        producerExecutor.execute(producerThread3);
        producerExecutor.execute(producerThread4);

        ConsumerThread consumerThread1 = new ConsumerThread(queue);
        ConsumerThread consumerThread2 = new ConsumerThread(queue);
        consumerExecutor.execute(consumerThread1);
        consumerExecutor.execute(consumerThread2);

        while (true) {
            //每5s检测一次是否还有线程存活
            Thread.sleep(5000);
            isPoolShutdown(producerExecutor, PRODUCER_POOL_NAME);
            isPoolShutdown(consumerExecutor, CONSUMER_POOL_NAME);
            if (producerExecutor.isShutdown() && consumerExecutor.isShutdown()) {
                break;
            }
        }
    }

    /**
     * isPoolShutdown 方法是 判断线程池是否shutdown
     *
     * @param executor 线程池
     * @param poolName 线程池名称
     * @author dongyinggang
     * @date 2020/6/29 15:18
     */
    private static void isPoolShutdown(ThreadPoolExecutor executor, String poolName) {
        if (!executor.isShutdown()) {
            int producerActiveCount = executor.getActiveCount();
            System.out.println(poolName + " 当前线程池活动线程数量为：" + producerActiveCount);
            if (0 == producerActiveCount) {
                System.out.println(poolName + " 线程池关闭");
                executor.shutdown();
            }
        }
    }
}
