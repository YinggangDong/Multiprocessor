package cn.dyg.threadbasic.lifecycle;

/**
 * JoinTest 类是 join 方法测试
 * 挂起当前线程，等待目标线程结束
 *
 * @author dongyinggang
 * @date 2021-02-20 11:17
 **/
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("子线程执行中...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程执行结束");
        });
        System.out.println("主线程正在执行,子线程调用join后等待子线程执行完毕才会继续执行");
        t.start();
        // 当执行t.join()时,主线程由于 join() 中调用了 wait() 方法而被阻塞
        // 因此，会等待子线程执行完毕，isAlive()为false时,才跳出while循环,才能够继续进行主线程
        t.join();
        System.out.println("主线程在子线程结束后才继续执行");
    }
}
