package cn.dyg.threadbasic.lifecycle;

/**
 * ThreadInterruptTest 类是 interrupt方法测试类
 *
 * @author dongyinggang
 * @date 2020-08-25 13:12
 **/
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadInterruptTest threadInterruptTest = new ThreadInterruptTest();
        Thread thread = new Thread(threadInterruptTest.runnable);
        thread.start();

        //1.主线程睡眠3s，会去执行thread的 interrupt 操作
        try {
            Thread.sleep(3000);
            //这里调用 join 方法后，下面的 interrupt 就不会在 thread 的输出执行完（用时10s）之前被调用了，
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("In Main");
            e.printStackTrace();
        }

        //调用 interrupt 方法进行线程的中止，会设置一个终止标志，但实际并不强制终止，由子线程自己考虑终止时间
        Long start= System.currentTimeMillis();
        thread.interrupt();
        //终止标志始终未变,原因是什么？
        while(!thread.isInterrupted()){
            System.out.println("子线程是否处于中断状态：" + thread.isInterrupted());
            Thread.sleep(1000);
        }
        System.out.println("子线程调用interrupt方法"+(System.currentTimeMillis()-start)+"ms后，进入了中止状态");


    }

    /**
     * 通过lambda表达式声明一个Runnable对象
     */
    Runnable runnable = () -> {
        int i = 0;
        //子线程每隔500ms就输出一次i值并+1
        try {
            while (i < 20) {
                Thread.sleep(500);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            //当子线程在 sleep 时调用了 interrupt 方法，会报错，此时线程依旧存活
            System.out.println("线程是否存活：" + Thread.currentThread().isAlive());
            System.out.println("线程是否处于中断状态：" + Thread.currentThread().isInterrupted());
            System.out.println("In Runnable");
            e.printStackTrace();
        }
    };
}
