package cn.dyg.producerconsumer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ConsumerThread 类是 消费者线程类
 *
 * @author dongyinggang
 * @date 2020-06-28 21:32
 **/
public class ConsumerThread implements Runnable {
    /**
     * 缓冲队列queue
     */
    private volatile LinkedBlockingQueue queue;

    public ConsumerThread(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public void stop() {
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "关闭");
    }

    @Override
    public void run() {
        boolean flag = false;
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "启动");
        int count = 0;
        while (!flag) {
            try {
                //消息出队
                Object messages = queue.poll();
                if (messages != null) {
                    count = 0;
                    System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "----->消费消息内容：" + messages);
                    Thread.sleep(2000);
                } else {
                    count++;
                    Thread.sleep(2000);
                    System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "----->队列暂无消息,消费者等待...");
                }
                //队列中如果10s没有消息,此时消费者线程
                if (count >= 5) {
                    System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "超过10s无消息，消费者不再消费");
                    stop();
                    flag = true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
