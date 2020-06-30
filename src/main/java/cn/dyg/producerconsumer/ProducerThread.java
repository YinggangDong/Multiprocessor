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

    private volatile boolean flag = false;

    public ProducerThread(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void stop() {
        flag = true;
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "关闭");
    }

    public void sleep(int millis) throws InterruptedException {
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "休眠中...");
        Thread.sleep(millis);
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "休眠结束,运行中...");
    }


    @Override
    public void run() {

        int count = 0;
        System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + "启动");
        while (!flag) {
            try {
                //向阻塞队列中投递一条数据
                String message = "[Message] " + Thread.currentThread().getName()
                        + "-----" + messageNumber.getAndIncrement();
                //添加元素到队列里，成功返回true，失败返回false
                Boolean offerFlag = queue.offer(message);
                //如果投递成功,则睡眠500ms后再进行投递
                if(offerFlag){
                    System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + " : 投递 " + message);
                    Thread.sleep(500);
                }else{
                    //投递失败后,睡眠2s,并增加投递失败次数
                    System.out.println(DateUtil.getNow() + Thread.currentThread().getName() + " : 投递 " + message +" 失败");
                    sleep(2000);
                    count++;
                }
                //失败10次后,当前生产者线程停止投递
                if (count >= 10) {
                    stop();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
