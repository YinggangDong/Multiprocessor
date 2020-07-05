package cn.dyg.producerconsumer.selfqueue;

/**
 * Consumer 类是 消费者线程类
 *
 * @author dongyinggang
 * @date 2020-07-05 16:17
 **/
public class Consumer implements Runnable {

    private volatile SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    /**
     * 消费
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.pop();
        }
    }
}
