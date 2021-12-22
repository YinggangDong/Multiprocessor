package cn.dyg.keyword.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * LockFlagTest 类是 锁标志测试
 * 对于 64 位系统来说,对象头的第一个字节的7、8位信息如下：
 * 1.无锁态 01 第六位 0
 * 2.偏向锁 01 第六位 1
 * 3.轻量级锁 00
 * 4.重量级锁 10
 * 5.GC标记 11
 *
 * @author dongyinggang
 * @date 2021-03-04 16:41
 **/
public class LockFlagTest {

    public static void main(String[] args) throws InterruptedException {
//        noLock();
//        biasedLock();
//        lightLock();
        heavyLock();
    }

    /**
     * noLock 方法是 无锁状态（尽管无同步代码块,但实际是默认偏向锁）
     *
     * @author dongyinggang
     * @date 2021/3/4 17:06
     */
    private static void noLock() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        //1.无锁态 6/7/8位 0 01 但实际睡眠 5s 后，默认处于偏向锁状态
        System.out.println("1.无锁态");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        TimeUnit.SECONDS.sleep(10);
        Object obj1 = new Object();
        System.out.println(ClassLayout.parseInstance(obj1).toPrintable());

    }

    /**
     * biasedLock 方法是 偏向锁状态
     *
     * @author dongyinggang
     * @date 2021/3/4 17:56
     */
    private static void biasedLock() throws InterruptedException {
        //因为JVM的偏向锁有延迟启动机制，每个对象并不会在一开始就获得偏向锁，需要在程序运行后大概4-5秒之后才会获得
        //如果不sleep 5 s 会发现实际是个轻量级锁 00
        //避免偏向锁启动延迟的方法，还有更加直接的办法，
        // 启动时添加下列参数取消偏向锁启动延迟："-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0"
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        //2.偏向锁 6/7/8位 1 01
        synchronized (obj) {
            System.out.println("2.偏向锁态");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("ps:偏向锁态处理完同步代码块之后,不会降级为无锁态");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

    }

    /**
     * lightLock 方法是 轻量级锁
     * 两个或以上线程交替获取锁且未发生竞争时，变为轻量级锁
     *
     * @author dongyinggang
     * @date 2021/3/4 18:01
     */
    private static void lightLock() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        System.out.println("另起线程前,处于偏向锁态");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        //3.第一个线程,保持偏向锁状态
        new Thread(() -> {
            synchronized (obj) {
                System.out.println("第一个线程,保持偏向锁状态 ");
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());

            }
        }).start();
        TimeUnit.SECONDS.sleep(1);

        //3.轻量级锁 00, 第二个线程使用 obj,此时进入轻量级锁状态
        new Thread(() -> {
            synchronized (obj) {
                System.out.println("第二个线程,进入轻量级锁状态");
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());

            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程结束后,处于无锁态");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

    }

    /**
     * heavyLock 方法是 重量级锁测试
     * 两个或以上线程同时竞争锁时，膨胀为重量级锁
     *
     * @author dongyinggang
     * @date 2021/3/5 14:01
     */
    private static void heavyLock() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        System.out.println("另起线程前,处于偏向锁态 1 01");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        //第一次结束后,obj 处于无锁态,第二次,再次膨胀为重量级锁,再次结束后,又处于无锁态了
        for (int j = 0; j < 2; j++) {
            //4.重量级锁 多个线程争夺 obj 的 monitor 锁,直接膨胀为重量级锁
            for (int i = 0; i < 2; i++) {
                new Thread(() -> {
                    synchronized (obj) {
                        System.out.println("两个线程竞争,进入重量级锁状态 10");
                        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                    }
                }).start();
            }
            TimeUnit.SECONDS.sleep(2);
            System.out.println("第" + j + 1 + "轮竞争线程结束后,处于无锁态 0 01");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

    }
}
