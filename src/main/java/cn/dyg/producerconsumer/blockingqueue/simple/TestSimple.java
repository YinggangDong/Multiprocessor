package cn.dyg.producerconsumer.blockingqueue.simple;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TestSimple 类是 普通生产者消费者模式测试
 *
 * @author dongyinggang
 * @date 2020-06-29 21:34
 **/
public class TestSimple {

    public static void main(String[] args) {
        LinkedBlockingQueue<Product> queue = new LinkedBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,5,20, TimeUnit.SECONDS,new LinkedBlockingQueue<>(3));

        executor.execute(producer);
        executor.execute(consumer);


        while(true){
            int executorActiveCount = executor.getActiveCount();
            if (0 == executorActiveCount) {
                System.out.println("线程池关闭");
                executor.shutdown();
                break;
            }
            System.out.println("存活线程数:"+executorActiveCount);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
