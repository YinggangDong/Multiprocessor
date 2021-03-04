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
    }

    /**
     * 虽然使用了 synchronized
     * 但由于 obj 是局部变量，每次调用都会是新的对象，
     * 因此实际会被优化，执行锁消除，和没有 synchronized 的代码效率一样高
     */
    private static void synRemoveTest() {
        Object obj = new Object();
        synchronized (obj) {
            System.out.println("no monitor enter");
        }
    }
}
