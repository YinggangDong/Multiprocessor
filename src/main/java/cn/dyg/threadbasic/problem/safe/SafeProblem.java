package cn.dyg.threadbasic.problem.safe;

import cn.dyg.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * SafeProblem 类是 线程安全问题相关类
 * 1.通过手动加锁 synchronized 实现线程安全
 * 2.通过 JDK 提供的原子类保证线程安全,不需要再显式加锁
 *
 * @author dongyinggang
 * @date 2020-11-26 11:21
 **/
public class SafeProblem {

    private static long count = 0;
    /**
     * jdk提供的线程安全的Long类型
     */
    private static AtomicLong atomicCount = new AtomicLong(count);

    public static void main(String[] args) {
        SafeProblem safeProblem = new SafeProblem();
        //1.测试实例变量的线程安全问题
        safeProblem.instanceVariable();
        safeProblem.atomicTest();
    }

    /**
     * instanceVariable 方法是 实例变量值的线程安全测试
     *
     * @author dongyinggang
     * @date 2021/3/2 11:28
     */
    private void instanceVariable() {

        //1.创建线程池
        int threadSize = 4;
        ExecutorService executorService = ThreadUtil.buildThreadExecutor(threadSize);
        //2.创建线程计数器
        final CountDownLatch latch = new CountDownLatch(threadSize);
        long start = System.currentTimeMillis();
        //3.通过线程池启动4个线程进行count的运算
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                //加锁,则可以获取正确的count结果,不加锁,则结果值会出现问题
                synchronized (this) {
                    //循环次数
                    int cycleTime = 10000000;
                    for (int j = 0; j < cycleTime; j++) {
                        count++;
                    }
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完成");
                //当前子线程调用此方法，则计数减一
                latch.countDown();

            });
        }
        try {

            System.out.println("主线程" + Thread.currentThread().getName()
                    + "等待子线程执行完成...");
            //阻塞当前线程，直到计数器的值为0
            latch.await();
            //关闭线程池
            executorService.shutdown();
            System.out.println("主线程" + Thread.currentThread().getName()
                    + "开始执行...count为" + count);
            System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * atomicTest 方法是 jdk提供的原子性对象,不需要加锁即可进行
     *
     * @author dongyinggang
     * @date 2021/3/2 11:29
     */
    private void atomicTest() {
        //1.创建线程池
        int threadSize = 4;
        ExecutorService executorService = ThreadUtil.buildThreadExecutor(threadSize);
        //2.创建线程计数器
        final CountDownLatch latch = new CountDownLatch(threadSize);
        long start = System.currentTimeMillis();
        //3.通过线程池启动4个线程进行count的运算
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                //循环次数
                int cycleTime = 10000000;
                for (int j = 0; j < cycleTime; j++) {
                    //通过调用jdk提供的原子类,可以不需要加锁,但是效率会低很多,
                    // 本方法和上面加锁版的代码的效率要低四倍多
                    atomicCount.incrementAndGet();
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完成");
                //当前子线程调用此方法，则计数减一
                latch.countDown();

            });
        }
        try {

            System.out.println("主线程" + Thread.currentThread().getName()
                    + "等待子线程执行完成...");
            //阻塞当前线程，直到计数器的值为0
            latch.await();
            //关闭线程池
            executorService.shutdown();
            System.out.println("主线程" + Thread.currentThread().getName()
                    + "开始执行...atomicCount为" + atomicCount);
            System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
