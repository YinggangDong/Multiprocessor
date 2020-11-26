package cn.dyg.threadbasic.problem.safe;

import cn.dyg.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * SafeProblem 类是 线程安全问题相关类
 *
 * @author dongyinggang
 * @date 2020-11-26 11:21
 **/
public class SafeProblem {

    private long count = 0;

    public static void main(String[] args) {
        SafeProblem safeProblem = new SafeProblem();
        //1.测试实例变量的线程安全问题
        safeProblem.instanceVariable();
    }

    public void instanceVariable() {
        //1.创建线程池
        int threadSize = 10;
        ExecutorService executorService = ThreadUtil.buildThreadExecutor(threadSize);
        //2.创建线程计数器
        final CountDownLatch latch = new CountDownLatch(threadSize);
        //3.通过线程池启动10个线程进行count的运算
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                //循环次数
                int cycleTime = 10000;
                for (int j = 0; j < cycleTime; j++) {
                    count++;
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完成");
                //当前子线程调用此方法，则计数减一
                latch.countDown();

            });
        }
        try {

            System.out.println("主线程" + Thread.currentThread().getName()
                    + "等待子线程执行完成...");
            latch.await();//阻塞当前线程，直到计数器的值为0
            executorService.shutdown();
            System.out.println("主线程" + Thread.currentThread().getName()
                    + "开始执行...count为" + count);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
