package cn.dyg.producerconsumer.simple;

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
        for (int i = 0; i < 100; i++) {

            //如果队列满了
            if (queue.remainingCapacity() == 0) {
                System.out.println("队列已满,不再生产产品,通知消费者进行消费");
                this.notifyAll();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                queue.put(new Product(i));
                System.out.println("生产第" + i + "个产品");
                this.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
