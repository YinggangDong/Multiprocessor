package cn.dyg.keyword.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockExample 类是 Lock接口基础
 *
 * @author dongyinggang
 * @date 2021-02-22 11:07
 **/
public class LockExample {

    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockExample lockExample = new LockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> lockExample.func());
        executorService.execute(() -> lockExample.func());
        executorService.shutdown();
    }

    /**
     * func 方法是 通过显式锁lock进行并发编程
     *
     * @author dongyinggang
     * @date 2021/3/23 14:11
     */
    private void func() {
        //标准用法是在try之间进行lock，在finally中进行unlock
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " 输出 " + i);
            }
            System.out.println(Thread.currentThread().getName() + " 输出完毕");
        } finally {
            // 确保释放锁，从而避免发生死锁。
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放锁");
        }
    }
}
