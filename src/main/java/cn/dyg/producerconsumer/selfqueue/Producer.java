package cn.dyg.producerconsumer.selfqueue;

import cn.dyg.producerconsumer.Product;

/**
 * Producer 类是 生产者线程
 *
 * @author dongyinggang
 * @date 2020-07-05 16:19
 **/
public class Producer implements Runnable {

    /**
     * 自己实现的缓冲区对象
     */
    private volatile SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            container.push(new Product(i));
        }
    }
}