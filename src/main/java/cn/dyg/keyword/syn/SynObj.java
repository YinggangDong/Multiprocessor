package cn.dyg.keyword.syn;

/**
 * SynObj 类是 synchronized加锁对象
 * synchronized 是 Java 的一个关键字，它能够将代码块(方法)锁起来
 *
 * @author dongyinggang
 * @date 2020-12-09 13:12
 **/
public class SynObj {

    private static final int FIVE_INT = 5;
    private static final int SLEEP_MILLIS = 1000;
    private static final int TEN = 10;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    /**
     * staticMethod 给静态方法加锁,锁的是SynObj类
     *
     * @author dongyinggang
     * @date 2020/12/9 13:11
     */
    public static synchronized void staticMethod() {
        for (int i = 0; i < FIVE_INT; i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void doSomething() {
        int loopTimes = 0;
        while (loopTimes < TEN) {
            System.out.println("非同步方法可以被调用");
            try {
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loopTimes++;
        }
    }

    /**
     * instanceMethod 方法是 给实例方法加synchronized锁
     * 给普通方法加锁，锁的是对象实例，多个实例时会出现并发问题
     *
     * @author dongyinggang
     * @date 2020/11/27 16:28
     */
    public synchronized void instanceMethod() {
        for (int i = 0; i < FIVE_INT; i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronizedBlock 方法是 给代码块加synchronized锁
     * 如果是加this的话，实际与给实例方法加synchronized一样，都是锁了本类的实例
     *
     * @author dongyinggang
     * @date 2020/11/27 16:39
     */
    public void synchronizedBlock() {
        // 修饰代码块
        synchronized (this) {
            for (int i = 0; i < FIVE_INT; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(SLEEP_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void synchronizedBlockThis() {
        System.out.println("已进入 synchronizedBlockThis 方法，需要获取当前实例对象的锁才能继续执行...");
        // 修饰代码块
        synchronized (this) {
            System.out.println("synchronizedBlockThis 方法获取到当前实例的锁，继续执行");
            for (int i = 0; i < FIVE_INT; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(SLEEP_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * synchronizedBlockObj1 和 synchronizedBlockObj2 方法是 两个方法分别锁不同的对象
     * 这样对两个对象分别加锁，实际上是互不影响的，可以分别执行，不会阻塞另一个线程
     *
     * @author dongyinggang
     * @date 2020/12/9 19:41
     */
    public void synchronizedBlockObj1() {
        // 修饰代码块
        synchronized (lock1) {
            for (int i = 0; i < FIVE_INT; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(SLEEP_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void synchronizedBlockObj2() {
        // 修饰代码块
        synchronized (lock2) {
            for (int i = 0; i < FIVE_INT; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(SLEEP_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
