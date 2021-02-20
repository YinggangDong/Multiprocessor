package cn.dyg.threadbasic.create;

/**
 * ExtendThread 类是 继承Thread类的线程类
 *
 * @author dongyinggang
 * @date 2020-07-08 14:21
 **/
public class ExtendThread extends Thread {

    private String name;
    private boolean flag = true;

    public ExtendThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println(name + "is running");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit() {
        this.flag = false;
    }
}
