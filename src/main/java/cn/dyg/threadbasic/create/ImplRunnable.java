package cn.dyg.threadbasic.create;

/**
 * ImplRunnable 类是 实现Runnable接口的线程类
 *
 * @author dongyinggang
 * @date 2020-07-08 14:30
 **/
public class ImplRunnable implements Runnable{

    private String name;
    public ImplRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        boolean flag = true;
        while(flag){
            System.out.println(name + "is running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
