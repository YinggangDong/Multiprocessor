package cn.dyg.threadbasic.lifecycle;

/**
 * ThreadInterruptTest 类是 interrupt方法测试类
 *
 * @author dongyinggang
 * @date 2020-08-25 13:12
 **/
public class ThreadInterruptTest {
    public static void main(String[] args) {
        ThreadInterruptTest threadInterruptTest = new ThreadInterruptTest();
        Thread thread = new Thread(threadInterruptTest.runnable);
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("In Main");
            e.printStackTrace();
        }
        //中断
        thread.interrupt();

    }

    /**
     * 通过lambda表达式声明一个Runnable对象
     */
    Runnable runnable = () -> {
        int i = 0;

        try {
            while (i < 1000) {
                Thread.sleep(500);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            System.out.println("线程是否存活：" + Thread.currentThread().isAlive());
            System.out.println("线程是否处于中断状态：" + Thread.currentThread().isInterrupted());
            System.out.println("In Runnable");
            e.printStackTrace();
        }
    };
}
