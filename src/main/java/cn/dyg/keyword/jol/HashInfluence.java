package cn.dyg.keyword.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * HashInfluence 类是 hash 运算对锁的影响
 *
 * @author dongyinggang
 * @date 2021-03-06 16:01
 **/
public class HashInfluence {

    public static void main(String[] args) throws InterruptedException {

//        hash();
        hashJump();
    }


    /**
     * hash 方法是 进行 hash 运算后的锁
     * 若进行了 hash 运算,就不能进入偏向锁态了
     *
     * @author dongyinggang
     * @date 2021/3/6 16:05
     */
    private static void hash() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        //如果计算 identityHashCode 值,就不能进入偏向锁态了,因为没额外的地方放哈希码
        // 如果没计算哈希值,哈希值默认是0,计算了的话就会显示哈希值
        System.out.println("对象的哈希值 identityHashCode 值：" + Integer.toHexString(System.identityHashCode(obj)));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj) {
            System.out.println("计算了哈希值,不能膨胀为偏向锁,膨胀为轻量级锁");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

    }

    /**
     * hashJump 方法是 偏向锁态下,锁的膨胀
     * 偏向过程中执行哈希运算,直接膨胀为重量级锁,跳过轻量级锁
     *
     * @author dongyinggang
     * @date 2021/3/6 16:06
     */
    private static void hashJump() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object obj = new Object();
        synchronized (obj) {
            //偏向过程中执行哈希,直接膨胀为重量级锁,跳过轻量级锁
            System.out.println("对象的哈希值 identityHashCode 值：" + Integer.toHexString(System.identityHashCode(obj)));
            System.out.println("偏向锁状态,经过哈希值计算,直接膨胀为重量级锁");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("重量级锁已经释放,变更为无锁态");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
