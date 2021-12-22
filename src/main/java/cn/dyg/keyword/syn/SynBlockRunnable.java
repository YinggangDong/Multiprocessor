package cn.dyg.keyword.syn;

/**
 * SynBlockRunnable 类是 锁代码块的测试
 *
 * @author dongyinggang
 * @date 2020-12-09 20:02
 **/
public class SynBlockRunnable implements Runnable {

    private SynObj synObj;

    public SynBlockRunnable(SynObj synObj){
        this.synObj = synObj;
    }

    @Override
    public void run() {
        synObj.synchronizedBlock();
    }

    public static void main(String[] args) {

//        System.out.println("两个线程操作锁了同一实例this的");
//        twoThreadOneInstance();

//        System.out.println("两个线程分别锁两个实例this的");
//        twoThreadTwoInstance();

        System.out.println("两个线程调用不同方法，但锁一个实例的this的");
        twoThreadSynThis();
//
//        System.out.println("两个线程调用不同方法，锁一个实例的不同对象");
//        twoThreadSynTwoObj();
    }

    /**
     * twoThreadOneInstance 方法是 两个线程,一个对象,代码块加锁,锁 this 对象
     * 这种方式和锁实例方法是一样的,两个线程实际是在争夺对象 synObj 的资源,
     * 哪个线程竞争到了,就可以进入死循环,不断输出自己的线程名
     *
     * @author dongyinggang
     * @date 2020/12/9 20:23
     */
    private static void twoThreadOneInstance() {
        SynObj synObj = new SynObj();

        //两个线程
        Thread thread1 = new Thread(new SynBlockRunnable(synObj));
        Thread thread2 = new Thread(new SynBlockRunnable(synObj));
        thread1.start();
        thread2.start();
    }

    /**
     * twoThreadTwoInstance 方法是 两个线程，两个对象，代码块加锁,锁 this 对象
     * 这种方式和锁实例方法是一样的,锁 this 锁得是一个实例,
     * 因此,当我们两个线程传入的是不同对象的情况下,
     * 两个线程实际是都获得了自己对象的资源，都能够进行自己名字的输出
     *
     * @author dongyinggang
     * @date 2020/12/9 18:50
     */
    private static void twoThreadTwoInstance() {
        SynObj synObj1 = new SynObj();
        SynObj synObj2 = new SynObj();

        //两个线程两个对象
        Thread thread1 = new Thread(new SynBlockRunnable(synObj1));
        Thread thread2 = new Thread(new SynBlockRunnable(synObj2));
        thread1.start();
        thread2.start();
    }

    /**
     * twoThreadSynThis 方法是 两个线程，一个对象，两个方法分别锁了this
     * 两个线程看似调用了 SynObj 的两个不同实例方法,实例方法中都存在锁,并且都锁了this
     * 两个线程实际都在竞争实例的资源,谁竞争到了,就一直执行,另一个虽然是调用了不同方法,
     * 但由于始终获取不到锁,所以就无法执行
     * 如果这里是两个对象了，虽然都锁了this，但实际此this非彼this，两者会互不影响
     *
     * @author dongyinggang
     * @date 2020/12/9 20:29
     */
    private static void twoThreadSynThis() {
        SynObj synObj = new SynObj();

        //两个线程个对象
        Thread thread1 = new Thread(new SynBlockRunnable(synObj));
        Thread thread2 = new Thread(()->synObj.synchronizedBlockThis());
        thread1.start();
        thread2.start();
    }

    /**
     * twoThreadSynTwoObj 方法是 两个线程,一个对象的两个实例方法,锁了不同的Object
     * 此时,由于两个方法分别锁了lock1 和 lock2 ,是互不干扰的,因此可以同时被调用
     *
     * @author dongyinggang
     * @date 2020/12/9 20:36
     */
    private static void twoThreadSynTwoObj(){
        SynObj synObj = new SynObj();
        Thread thread1 = new Thread(()->synObj.synchronizedBlockObj1());
        Thread thread2 = new Thread(()->synObj.synchronizedBlockObj2());
        thread1.start();
        thread2.start();
    }
}
