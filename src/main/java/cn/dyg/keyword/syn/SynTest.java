package cn.dyg.keyword.syn;

/**
 * SynTest 类是 synchronized 锁
 *
 * @author dongyinggang
 * @date 2020-11-27 14:16
 **/
public class SynTest {

    public static void main(String[] args) {

//        twoThreadOneInstance();
        twoThreadTwoInstance();
    }

    /**
     * twoThreadOneInstance 方法是 两个线程,一个对象,实例方法加锁
     * 可以看到此时的输出全是一个线程名称，没有输出第二个线程的名字。
     * 对普通方法加synchronized关键字，锁的是对象，如果两个线程都使用同一个对象来生成线程，
     * 那么就出现了第二个启动的线程始终在等待第一个线程结束的情况。
     * Spring中默认是单例模式的，因此方法的锁能够起到相应的作用
     *
     * @author dongyinggang
     * @date 2020/12/9 18:40
     */
    private static void twoThreadOneInstance()  {
        SynObj synObj = new SynObj();

        //两个线程同一个对象
        Thread thread1 = new Thread(new SynRunnable(synObj));
        Thread thread2 = new Thread(new SynRunnable(synObj));
        thread1.start();
        thread2.start();
    }

    /**
     * twoThreadTwoInstance 方法是 两个线程，两个对象，实例方法加锁
     * 两个线程交替输出，没有达到锁住方法的目的
     *
     * @author dongyinggang
     * @date 2020/12/9 18:50
     */
    private static void twoThreadTwoInstance() {
        SynObj synObj1 = new SynObj();
        SynObj synObj2 = new SynObj();

        //两个线程两个对象
        Thread thread1 = new Thread(new SynRunnable(synObj1));
        Thread thread2 = new Thread(new SynRunnable(synObj2));
        thread1.start();
        thread2.start();
    }


}
