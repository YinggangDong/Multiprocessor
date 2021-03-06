package cn.dyg.producerconsumer.blockingqueue.complex;

import cn.dyg.util.DateUtil;

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
                //消息出队,如果messages为null,则说明当前队列中无消息,
                Object messages = queue.poll();
                if (messages != null) {
                    count = 0;
                    System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "----->消费消息内容：" + messages);
                } else {
                    //若无消息,则等待2s再进行执行
                    System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "----->队列暂无消息,消费者等待...");
                    count++;
                    Thread.sleep(2000);
                }
                //队列中如果10s没有消息,此时消费者线程不再消费
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
