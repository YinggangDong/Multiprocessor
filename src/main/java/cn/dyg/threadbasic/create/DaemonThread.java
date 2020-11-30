package cn.dyg.threadbasic.create;

/**
 * DaemonThread 类是 守护线程类
 *
 * @author dongyinggang
 * @date 2020-07-13 11:14
 **/
public class DaemonThread implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName()+"这是一个守护线程");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
