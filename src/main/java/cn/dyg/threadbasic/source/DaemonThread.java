package cn.dyg.threadbasic.source;

/**
 * DaemonThread 类是 守护线程类
 *
 * @author dongyinggang
 * @date 2020-07-13 11:14
 **/
public class DaemonThread implements Runnable{

    @Override
    public void run() {
        //守护线程创建的子线程进行自己线程名称的输出,该子线程未显式声明为守护线程，但实际也是一个守护线程
        Thread thread = new Thread(
                ()-> System.out.println(Thread.currentThread().getName()+"是否守护线程："+
                        Thread.currentThread().isDaemon()),
                "守护线程创建的子线程");
        thread.start();
        //守护线程是一个死循环
        while(true){
            System.out.println(Thread.currentThread().getName()+"这是一个守护线程");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
