package cn.dyg.producerconsumer.blockingqueue.simple;

import cn.dyg.producerconsumer.Product;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Consumer 类是 消费者线程类
 *
 * @author dongyinggang
 * @date 2020-06-29 21:26
 **/
public class Consumer implements Runnable {

    /**
     * 缓冲队列queue
     */
    private volatile LinkedBlockingQueue<Product> queue;

    public Consumer(LinkedBlockingQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        for (int i = 1; i <= 1000; i++) {
            //若队列中为空,则消费者等待,并通知生产者进行生产
            if (queue.isEmpty()) {
                System.out.println("队列为空,暂停消费,并且通知生产者进行生产...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = null;
            try {
                product = queue.take();
                System.out.println("消费第" + product.getId() + "个产品");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
