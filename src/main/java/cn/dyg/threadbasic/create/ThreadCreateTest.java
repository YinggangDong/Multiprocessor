package cn.dyg.threadbasic.create;

/**
 * ThreadCreateTest 类是 线程创建相关类
 *
 * @author dongyinggang
 * @date 2020-07-08 14:20
 **/
public class ThreadCreateTest {

    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread("继承Thread类的线程类实例");
        ImplRunnable implRunnable = new ImplRunnable("实现Runnable接口的线程类实例");
        //创建守护线程实例
        DaemonThread daemonThread = new DaemonThread();
        Thread thread = new Thread(daemonThread,"守护线程---->");

        extendThread.start();
        new Thread(implRunnable).start();

        //声明为守护线程并启动
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.setName("守护线程改名了---->");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //停止线程运行
        extendThread.exit();
        implRunnable.exit();

    }
}
