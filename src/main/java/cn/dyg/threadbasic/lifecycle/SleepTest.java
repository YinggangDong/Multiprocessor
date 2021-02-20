package cn.dyg.threadbasic.lifecycle;

/**
 * SleepTest 类是 sleep方法测试
 *
 * @author dongyinggang
 * @date 2021-02-20 08:57
 **/
public class SleepTest {

    public static void main(String[] args) {
        testLock();
    }

    /**
     * testSleep 方法是 测试test方法
     *
     * @author dongyinggang
     * @date 2021/2/20 10:21
     */
    private static void testSleep() {
        Thread t = new Thread(() -> {
            System.out.println("sub is running,status:" + Thread.currentThread().getState());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sub terminated,status：" + Thread.currentThread().getState());
        });
        System.out.println("子线程状态" + t.getState());
        t.start();
        //在子线程 terminated 之前持续输出其状态,输出结果不固定,可能存在 BLOCKED 状态（未获取到）
        while (true) {
            System.out.println("子线程状态" + t.getState());
            if (t.getState().equals(Thread.State.TERMINATED)) {
                System.out.println("子线程状态" + t.getState());
                return;
            }
        }
    }

    /**
     * testLock 方法是 测试sleep方法不释放锁
     *
     * @author dongyinggang
     * @date 2021/2/20 10:24
     */
    private static void testLock() {

        Object object = new Object();

        new Thread(() -> {
            System.out.println("线程A竞争锁ing...");
            synchronized (object) {
                System.out.println("子线程A获取到object锁,sleep时不释放");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程A执行完毕,sleep结束,释放object锁");
            }
        }).start();

        new Thread(() -> {
            System.out.println("线程B竞争锁ing...");
            synchronized (object) {
                System.out.println("子线程B获取到object锁,sleep时不释放");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程B执行完毕,sleep结束,释放object锁");
            }
        }).start();
    }
}
