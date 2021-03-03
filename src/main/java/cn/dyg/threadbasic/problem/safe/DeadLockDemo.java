package cn.dyg.threadbasic.problem.safe;

/**
 * DeadLockDemo 类是 死锁模拟示例代码
 *
 * @author dongyinggang
 * @date 2021-03-03 10:34
 **/
public class DeadLockDemo {

    /**
     * 锁对象 lockObj1
     */
    private static Object lockObj1 = new Object();

    /**
     * 锁对象 lockObj2
     */
    private static Object lockObj2 = new Object();

    public static void main(String[] args) {

        //线程A,先获取 lockObj1 的锁,再获取 lockObj2 的锁
        new Thread(() -> {
            synchronized (lockObj1) {
                try {
                    System.out.println("线程A获取到 lockObj1 对象锁");
                    //sleep 100 ms 让另一个线程获取下一把锁
                    Thread.sleep(100);
                    synchronized (lockObj2) {
                        System.out.println("线程A获取到 lockObj2 对象锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //线程B,先获取 lockObj2 的锁,再获取 lockObj1 的锁
        new Thread(() -> {
            synchronized (lockObj2) {
                try {
                    System.out.println("线程B获取到 lockObj2 对象锁");
                    //sleep 100 ms 让另一个线程获取下一把锁
                    Thread.sleep(100);
                    synchronized (lockObj1) {
                        System.out.println("线程B获取到 lockObj1 对象锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
