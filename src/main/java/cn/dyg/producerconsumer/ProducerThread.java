package cn.dyg.producerconsumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProducerThread 类是 生产者线程类
 *
 * @author dongyinggang
 * @date 2020/6/28 21:16
 **/
public class ProducerThread implements Runnable {

    /**
     * 缓冲队列queue
     */
    private volatile LinkedBlockingQueue<String> queue;
    private static AtomicInteger messageNumber = new AtomicInteger(1);


    public ProducerThread(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void stop() {
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "关闭");
    }

    @Override
    public void run() {

        boolean flag = false;
        int count = 0;
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "启动");
        while (!flag) {
            try {
                //向阻塞队列中投递一条数据
                String message = "[Message] " + Thread.currentThread().getName()
                        + "-----" + messageNumber.getAndIncrement();
                queue.put(message);
                System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + " : 投递 " + message);
                Thread.sleep(500);
                count++;
                if (count >= 100) {
                    stop();
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
