package cn.dyg.threadbasic.create;

/**
 * ThreadCreate 类是 线程创建相关类
 *
 * @author dongyinggang
 * @date 2020-07-08 14:20
 **/
public class ThreadCreate {

    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread("继承Thread类的线程类实例");
        ImplRunnable implRunnable = new ImplRunnable("实现Runnable接口的线程类实例");
        extendThread.start();
        new Thread(implRunnable).start();
    }
}
