package cn.dyg.threadbasic.create;

/**
 * ExtendThread 类是 继承Thread类的线程类
 *
 * @author dongyinggang
 * @date 2020-07-08 14:21
 **/
public class ExtendThread extends  Thread{

    private String name;

    public ExtendThread(String name){
        this.name = name;
    }

    @Override
    public void run(){
        boolean flag = true;
        while(flag){
            System.out.println(name + "is running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
