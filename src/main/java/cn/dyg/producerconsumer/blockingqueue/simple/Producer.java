package cn.dyg.producerconsumer.blockingqueue.simple;

import cn.dyg.producerconsumer.Product;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Producer 类是 生产者线程类
 *
 * @author dongyinggang
 * @date 2020-06-29 21:12
 **/
public class Producer implements Runnable {
    /**
     * 缓冲队列queue
     */
    private volatile LinkedBlockingQueue<Product> queue;

    public Producer(LinkedBlockingQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        for (int i = 1; i <= 1000; i++) {
            //如果队列满了
            if (queue.remainingCapacity() == 0) {
                System.out.println("队列已满,不再生产产品,通知消费者进行消费");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                queue.put(new Product(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产第" + i + "个产品");

        }
    }
}
