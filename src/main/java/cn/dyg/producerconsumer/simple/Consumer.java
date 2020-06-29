package cn.dyg.producerconsumer.simple;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Consumer 类是
 *
 * @author dongyinggang
 * @date 2020-06-29 21:26
 **/
public class Consumer implements Runnable {

    /**
     * 缓冲队列queue
     */
    private LinkedBlockingQueue<Product> queue;

    public Consumer(LinkedBlockingQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 100; i++) {
            //若队列中为空,则消费者等待,并通知生产者进行生产
            if (queue.isEmpty()) {
                System.out.println("队列为空,暂停消费,并且通知生产者进行生产...");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = queue.poll();
            //当获取到产品不为空时,进行消费
            if (product != null) {
                System.out.println("消费第" + product.getId() + "个产品");
            }
            //消费完成后,通知生产者进行生产
            this.notifyAll();

        }

    }
}
