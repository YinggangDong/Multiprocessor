package cn.dyg.threadbasic.source;

import cn.dyg.threadbasic.create.ImplRunnable;

/**
 * ThreadSourceTest 类是 Thread 类常用方法解析
 *
 * @author dongyinggang
 * @date 2020-11-30 10:46
 **/
public class ThreadSourceTest {

    public static void main(String[] args) {
        constructor();
//        threadName();
    }

    /**
     * constructor 方法是 常见构造方法
     * 1.无参构造
     * 2.指定线程名称的线程
     * 3.指定 runnable 实现类的线程
     * 4.指定 runnable 和线程名称的线程
     * 调用方法实际是调用了 init 方法
     *
     * @author dongyinggang
     * @date 2020/11/30 15:17
     */
    public static void constructor(){
        //1.无参构造
        Thread thread = new Thread();
        System.out.println(thread.getName());
        //2.指定线程名称的线程
        thread = new Thread("t-1");

        Runnable runnable = new ImplRunnable("r-1");
        //3.指定 runnable 实现类的线程
        thread = new Thread(runnable);
        System.out.println(thread.getName());
        //4.指定 runnable 和线程名称的线程
        thread = new Thread(runnable,"t-2");

    }

    /**
     * threadName 方法是 设置线程名称的两种方式
     * 注：
     * 若没有指定线程名称,会使用"Thread-x"(x指线程初始数量,是 Thread 类的 threadInitNumber)
     *
     * @author dongyinggang
     * @date 2020/12/1 18:14
     */
    public static void threadName(){
        //1.通过构造方法设置线程名称
        Thread thread = new Thread(ThreadSourceTest::outputThreadName,"t-1");

        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //2.通过setName方法设置线程名称
        thread.setName("t-2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * outputThreadName 方法是 输出线程名称的方法
     *
     * @author dongyinggang
     * @date 2020/12/1 19:20
     */
    private static void outputThreadName(){
        int i = 0;
        int times = 30;
        while(i<times){
            System.out.println(Thread.currentThread().getName()+" is running");
            try {
                i++;
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
