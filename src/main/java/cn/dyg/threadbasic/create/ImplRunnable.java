package cn.dyg.threadbasic.create;

/**
 * ImplRunnable 类是 实现Runnable接口的线程类
 *
 * @author dongyinggang
 * @date 2020-07-08 14:30
 **/
public class ImplRunnable implements Runnable {

    private String name;

    private boolean flag = true;

    public ImplRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println(name + "is running");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit() {
        this.flag = false;
    }
}
