package cn.dyg.threadbasic.create;

import java.util.concurrent.FutureTask;

/**
 * ThreadCreateTest 类是 线程创建相关类
 *
 * @author dongyinggang
 * @date 2020-07-08 14:20
 **/
public class ThreadCreateTest {

    public static void main(String[] args) {
//        extendTest();
//        implTest();
        callableTest();
//        daemonTest();

    }

    /**
     * extendTest 方法是 通过继承Thread类实现线程（不常用）
     *
     * @author dongyinggang
     * @date 2020/11/30 8:20
     */
    public static void extendTest() {
        ExtendThread extendThread = new ExtendThread("extend-t-1");
        ExtendThread extendThread2 = new ExtendThread("extend-t-2");
        extendThread.start();
        extendThread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //停止线程运行
        extendThread.exit();
        extendThread2.exit();
    }

    /**
     * implTest 方法是 实现Runnable接口的线程类实例
     *
     * @author dongyinggang
     * @date 2020/11/30 8:20
     */
    public static void implTest() {
        ImplRunnable implRunnable = new ImplRunnable("实现Runnable接口的线程类实例");
        new Thread(implRunnable).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //停止线程
        implRunnable.exit();

    }


    /**
     * callableTest 方法是 通过实现 callable 接口创建线程,
     * 需要配合 futureTask 使用来获取返回值
     *
     * @author dongyinggang
     * @date 2020/11/30 8:58
     */
    public static void callableTest() {
        CallableThread callableThread = new CallableThread();

        /*
         futureTask 实现了 RunnableFuture 接口,
         RunnableFuture则继承了 Runnable, Future 两个接口,
         因此实际上 new Thread(futureTask) 调用到的 Thread 类的构造方法还是 public Thread(Runnable target)
         */
        FutureTask<Integer> futureTask = new FutureTask<>(callableThread);

        new Thread(futureTask).start();


        try {
            //cancel方法不做展开
//            futureTask.cancel(true);

            while (!futureTask.isDone()) {
                System.out.println("futureTask未完成");
                Thread.sleep(1);
            }

            //通过get方法接收线程返回值,get方法会阻塞线程,直到返回结果
            Integer sum = futureTask.get();
            System.out.println("实现callable接口的线程计算结果：" + sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
