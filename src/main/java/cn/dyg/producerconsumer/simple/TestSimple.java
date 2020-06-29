package cn.dyg.producerconsumer.simple;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TestSimple 类是
 *
 * @author dongyinggang
 * @date 2020-06-29 21:34
 **/
public class TestSimple {

    public static void main(String[] args) {
        LinkedBlockingQueue<Product> queue = new LinkedBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,5,2, TimeUnit.SECONDS,new LinkedBlockingQueue<>(3));

        executor.execute(producer);
        executor.execute(consumer);


    }
}
