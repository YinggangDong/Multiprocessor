package cn.dyg.keyword.syn;

/**
 * SynObj 类是 synchronized加锁对象
 * synchronized 是 Java 的一个关键字，它能够将代码块(方法)锁起来
 *
 * @author dongyinggang
 * @date 2020-12-09 13:12
 **/
public class SynObj {

    Object object1 = new Object();
    Object object2 = new Object();

    /**
     * staticMethod 给静态方法付加锁,锁的是SynObj类
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
     * test2 方法是 给代码块加synchronized锁
     * 如果是加this的话，实际与给实例方法加synchronized一样，都是锁了本类的实例
     *
     * @author dongyinggang
     * @date 2020/11/27 16:39
     */
    public synchronized void synchroniazeBlock(){
        // 修饰代码块
        synchronized (this){
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
