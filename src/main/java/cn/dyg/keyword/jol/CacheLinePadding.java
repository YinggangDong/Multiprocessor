package cn.dyg.keyword.jol;

import sun.misc.Contended;

/**
 * CacheLinePadding 类是测试缓存行对齐对于性能的优化作用。
 * 伪共享问题
 * Java8的Contended注解解决伪共享问题
 *
 * @author dongyinggang
 * @date 2021-02-24 10:59
 **/
public class CacheLinePadding {
    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 10000000; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 10000000; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) + "纳秒");
    }

    private static class Padding {
        //一个long是8个字节，一共7个long
//        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    /**
     * Contended注解解决伪共享问题
     */
    @Contended
    private static class T extends Padding {
        //x变量8个字节，加上Padding中的变量，刚好64个字节，独占一个缓存行。
        public volatile long x = 0L;
    }
}

