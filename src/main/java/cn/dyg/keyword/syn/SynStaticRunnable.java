package cn.dyg.keyword.syn;

/**
 * SynStaticRunnable 类是 锁静态方法
 *
 * @author dongyinggang
 * @date 2020-12-09 19:35
 **/
public class SynStaticRunnable implements Runnable {

    private SynObj synObj;

    public SynStaticRunnable(SynObj synObj){
        this.synObj = synObj;
    }

    @Override
    public void run() {
        //调用加锁的静态方法
        SynObj.staticMethod();
        //如果使用实例来调用静态方法,编译器会提示但运行不会报错,不建议这样用
//        synObj.staticMethod();
    }

    public static void main(String[] args) {
        twoThreadTwoInstance();
        //其他方法依然可以正常调用。
//        doSomething();
    }

    /**
     * twoThreadTwoInstance 方法是 两个线程，两个对象，静态方法加锁
     * 虽然是两个对象，但是实际锁静态方法时锁的是类，因此先执行的线程会阻塞其他线程
     *
     * @author dongyinggang
     * @date 2020/12/9 18:50
     */
    private static void twoThreadTwoInstance() {
        SynObj synObj1 = new SynObj();
        SynObj synObj2 = new SynObj();

        //两个线程两个对象
        Thread thread1 = new Thread(new SynStaticRunnable(synObj1));
        Thread thread2 = new Thread(new SynStaticRunnable(synObj2));
        thread1.start();
        thread2.start();
    }

    private static void doSomething(){
        while(true){
            System.out.println("其他方法可以被调用");
        }
    }
}
