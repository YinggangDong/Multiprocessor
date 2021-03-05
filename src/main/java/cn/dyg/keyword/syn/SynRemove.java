package cn.dyg.keyword.syn;

/**
 * SynRemove 类是 锁消除,JDK 对 synchronized 的优化措施
 *
 * @author dongyinggang
 * @date 2021-03-04 15:17
 **/
public class SynRemove {

    public static void main(String[] args) {
        synRemoveTest();
        synRemoveTest1();
    }

    /**
     * 虽然使用了 synchronized
     * 但由于 obj 是局部变量，每次调用都会是新的对象，
     * 因此实际会被优化，执行锁消除，和没有 synchronized 的代码效率一样高
     */
    private static void synRemoveTest() {
        long start = System.currentTimeMillis();
        Object obj = new Object();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            synchronized (obj) {
                sb.append(i);
            }
        }
        System.out.println("有 synchronized 循环结束,耗时"
                + (System.currentTimeMillis() - start) + "ms");

    }

    private static void synRemoveTest1() {
        long start = System.currentTimeMillis();
        Object obj = new Object();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(i);
        }
        System.out.println("无 synchronized 循环结束,耗时"
                + (System.currentTimeMillis() - start) + "ms");

    }
}
