package cn.dyg.threadbasic.lifecycle;

/**
 * ThreadInterruptTest 类是 interrupt方法测试类
 *
 * @author dongyinggang
 * @date 2020-08-25 13:12
 **/
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        //1.创建并启动线程
        Thread thread = new Thread(runnable, "sub-thread");
        thread.start();
        System.out.println("终断前子线程状态" + thread.getState());
        //2.调用 interrupt 方法进行线程的中止，会设置一个终止标志，但实际并不强制终止，由子线程自己考虑终止时间
        thread.interrupt();
        //3.主线程休眠2s后再看子线程状态
        Thread.sleep(2000);
        //4.成功终止后,线程处于 TERMINATED 状态,终止标志为 false
        System.out.println("--------------- 经过 2s 的等待后 ---------------");
        System.out.println("终断后子线程状态 " + thread.getState() + "\n终止标志：" + thread.isInterrupted());

    }

    /**
     * 通过lambda表达式声明一个Runnable对象
     */
    static Runnable runnable = () -> {
        int i = 0;
        //子线程每隔500ms就输出一次i值并+1
        try {
            int times = 10;
            //2a.如果主线程终止操作成功,则会结束循环
            while (i < times && !Thread.currentThread().isInterrupted()) {
                System.out.println("子线程输出" + i++);
                //2b.如果处于阻塞状态(可由wait、sleep、join三个方法引起),会抛出InterruptedException异常,被捕获
                Thread.sleep(500);
            }
            System.out.println("子线程输出完毕");
        } catch (InterruptedException e) {
            //当子线程抛出异常，线程依旧存活,且终止状态被置为false
            System.out.println("线程被终止时的状态：" + Thread.currentThread().getState());
            System.out.println("线程是否处于终止状态：" + Thread.currentThread().isInterrupted());
            e.printStackTrace();
            //捕获到异常后，需要重新进行终止,才会将终止状态变更为true
            Thread.currentThread().interrupt();
            System.out.println("再次终止后:");
            System.out.println("线程是否处于终止状态：" + Thread.currentThread().isInterrupted());
            System.out.println("线程再次终止后的状态：" + Thread.currentThread().getState());
        }
    };
}
