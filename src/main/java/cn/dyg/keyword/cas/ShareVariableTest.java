package cn.dyg.keyword.cas;

import java.util.concurrent.CountDownLatch;

/**
 * ShareVariableTest 类是 测试主线程和子线程是否能够共享变量
 * 当主线程和子线程共享的变量是非基础类型的变量时,可以在并行情况下通过锁保证子线程和主线程的一致
 *
 * @author dongyinggang
 * @date 2020-11-27 10:59
 **/
public class ShareVariableTest {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("cpu核数" + Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
//            runnableTest();
            threadTest();
        }

    }

    public static void runnableTest() throws InterruptedException {
        //新建对象，准备传递给线程
        Num i = new Num(0);
        int threadSize = 4;
        CountDownLatch latch = new CountDownLatch(threadSize);
        OwnRunnable ownRunnable = new OwnRunnable(i, latch);
        for (int j = 0; j < threadSize; j++) {
            //新建线程，并启动
            new Thread(ownRunnable).start();
        }
        latch.await();
        //获取目前对象i的数值
        System.out.println("主线程中i的值变为了：" + i.i);
    }

    public static void threadTest() throws InterruptedException {
        //新建对象，准备传递给线程
        Num i = new Num(0);
        int threadSize = 4;
        CountDownLatch latch = new CountDownLatch(threadSize);
        for (int j = 0; j < threadSize; j++) {
            //新建线程，并启动
            new OwnThread(i, latch).start();
        }
        latch.await();
        //获取目前对象i的数值
        System.out.println("主线程中i的值变为了：" + i.i);
    }
}

/**
 * 子线程类
 */
class OwnThread extends Thread {
    /**
     * 申明对象，默认null，就是没有指向任何实体
     */
    private Num id;
    /**
     * 申明int变量。因为系统默认初始化为0，所以应该是定义一个int变量
     */
    private int sno;

    private CountDownLatch latch;

    OwnThread(Num id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        int count = 1000;
        for (int i = 0; i < count; i++) {
            //这里如果锁this的话会出问题,
            synchronized (this.id) {
                //保存id.i的数值，到线程私有变量sno
                sno = id.i;
                id.i++;
                //sleep操作让线程进行了切换,否则会发现经常是1个线程全执行完了才有其他的线程介入
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //如果打印的话，通常情况下会和4000的差值小,不打印则差值很大
//                System.out.println(this.getName() + "," + sno);
            }

        }
        latch.countDown();

    }
}

/**
 * 实现Runnable接口的类
 */
class OwnRunnable implements Runnable {
    /**
     * 申明对象，默认null，就是没有指向任何实体
     */
    private Num id;
    /**
     * 申明int变量。因为系统默认初始化为0，所以应该是定义一个int变量
     */
    private int sno;

    private CountDownLatch latch;

    OwnRunnable(Num id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        int count = 1000;
        for (int i = 0; i < count; i++) {
            synchronized (this) {
                //保存id.i的数值，到线程私有变量sno
                sno = id.i;
                id.i++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println( Thread.currentThread().getName()+ "," + sno);
        }
        latch.countDown();

    }
}

/**
 * Num类
 */
class Num {
    int i;

    Num(int i) {
        this.i = i;
    }
}
