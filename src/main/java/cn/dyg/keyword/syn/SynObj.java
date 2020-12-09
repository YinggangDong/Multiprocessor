package cn.dyg.keyword.syn;

/**
 * SynObj 类是 synchronized加锁对象
 * synchronized 是 Java 的一个关键字，它能够将代码块(方法)锁起来
 *
 * @author dongyinggang
 * @date 2020-12-09 13:12
 **/
public class SynObj {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    /**
     * staticMethod 给静态方法加锁,锁的是SynObj类
     *
     * @author dongyinggang
     * @date 2020/12/9 13:11
     */
    public synchronized static void staticMethod(){
        while (true){
            System.out.println(Thread.currentThread().getName());
        }
    }

    /**
     * instanceMethod 方法是 给实例方法加synchronized锁
     * 给普通方法加锁，锁的是对象实例，多个实例时会出现并发问题
     *
     * @author dongyinggang
     * @date 2020/11/27 16:28
     */
    public synchronized void instanceMethod(){
        while (true){
            System.out.println(Thread.currentThread().getName());
        }
    }

    /**
     * synchronizedBlock 方法是 给代码块加synchronized锁
     * 如果是加this的话，实际与给实例方法加synchronized一样，都是锁了本类的实例
     *
     * @author dongyinggang
     * @date 2020/11/27 16:39
     */
    public synchronized void synchronizedBlock(){
        // 修饰代码块
        synchronized (this){
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public synchronized void synchronizedBlockThis(){
        // 修饰代码块
        synchronized (this){
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    /**
     * synchronizedBlockObj1 和 synchronizedBlockObj1 方法是 两个方法分别锁不同的对象
     * 这样对两个对象分别加锁，实际上是互不影响的，可以分别执行，不会阻塞另一个线程
     *
     * @author dongyinggang
     * @date 2020/12/9 19:41
     */
    public void synchronizedBlockObj1(){
        // 修饰代码块
        synchronized (lock1){
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public void synchronizedBlockObj2(){
        // 修饰代码块
        synchronized (lock2){
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
