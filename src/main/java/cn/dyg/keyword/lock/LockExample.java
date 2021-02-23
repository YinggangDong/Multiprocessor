package cn.dyg.keyword.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockExample 类是 锁ReentrantLock基础
 *
 * @author dongyinggang
 * @date 2021-02-22 11:07
 **/
public class LockExample {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockExample lockExample = new LockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> lockExample.func());
        executorService.execute(() -> lockExample.func());
        executorService.shutdown();
    }

    private void func() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " 输出 " + i);
            }
            System.out.println(Thread.currentThread().getName() + " 输出完毕");
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁。
            System.out.println(Thread.currentThread().getName() + " 释放锁");
        }
    }
}
