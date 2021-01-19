package cn.dyg.threadpool.create;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PoolCreateTest 类是 线程池创建Test
 * Executors提供了四种默认的线程池创建方法
 *
 * @author dongyinggang
 * @date 2020-07-11 14:18
 **/
public class PoolCreateTest {

    public static void main(String[] args) throws InterruptedException {
        cachedThreadPoolTest();
//        ScheduledThreadPoolTest();
    }

    /**
     * cachedThreadPoolTest 方法是 测试cached线程池
     * 特点
     * 1.这是一个线程数量可以“无限”扩大（不能超过整型最大值）的线程池；
     * 2.比较适合处理执行时间比较小的任务；
     * 3.corePoolSize为0，maximumPoolSize为无限大，意味着线程数量可以无限大；
     * 4.keepAliveTime为60S，意味着线程空闲时间超过60S就会被杀死；
     * 5.采用SynchronousQueue装等待的任务，这个阻塞队列没有存储空间，这意味着只要有请求到来，
     * 就必须要找到一条工作线程处理他，如果当前没有空闲的线程，那么就会再创建一条新的线程。
     * 隐患：
     * 由于最大线程数无限大，异常情况可能导致线程数量太多，线程调度反而成为效率瓶颈。
     *
     * @author dongyinggang
     * @date 2021/1/18 19:13
     */
    private static void cachedThreadPoolTest() throws InterruptedException {
        //创建一个缓存线程池cachedThreadPool
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            cachedThreadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + " is running"));
        }
    }

    /**
     * fixedThreadPoolTest 方法是 测试fixed线程池
     * 特点：
     * 1.这是一种线程数量固定的线程池，因为corePoolSize和maximunPoolSize都为用户设定的线程数量nThreads；
     * 2.keepAliveTime为0，意味着一旦有多余的空闲线程，就会被立即停止掉，不过因为最多只有nThreads个线程，
     * 且corePoolSize和maximunPoolSize值一致，所以这个值无法发挥作用；
     * 3.阻塞队列采用了LinkedBlockingQueue，它是一个无界队列，由于阻塞队列是一个无界队列，因此永远不可能拒绝任务。
     * 隐患：
     * 如果持续使用线程池开启新线程，且线程的阻塞时间较长，或出现了异常的阻塞情况致使线程不能执行新任务，会
     * 导致任务在队列中不断累积，最终导致内存溢出。
     *
     * @author dongyinggang
     * @date 2021/1/18 19:10
     */
    private static void fixedThreadPoolTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + " is running"));
        }
    }

    /**
     * SingleThreadPoolTest 方法是 Single线程池测试
     * 特点：
     * 1.只有一个线程，使用了无界队列LinkedBlockingQueue，某种意义上等同于newFixedThreadPool(1);
     * 2.因为只有一个线程，所以能够保证所有任务是FIFO地执行。
     * 隐患：
     * 好像对线程进行了池化，实际未能有效的实现池化用来提高效率的目标。
     *
     * @author dongyinggang
     * @date 2021/1/18 19:17
     */
    private static void SingleThreadPoolTest() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            singleThreadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + " is running"));
        }
    }

    private static void ScheduledThreadPoolTest() {
        AtomicInteger number = new AtomicInteger();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 3; i++) {
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + number.incrementAndGet() + "周期线程运行当前时间【" +
                            LocalDateTime.now() + "】");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 3L, TimeUnit.SECONDS);
        }
        System.out.println("主线程运行,当前时间【" +LocalDateTime.now() + "】");
    }
}
