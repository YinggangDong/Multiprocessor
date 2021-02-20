package cn.dyg.threadbasic.lifecycle;

/**
 * YieldTest 类是 yield方法测试
 *
 * @author dongyinggang
 * @date 2021-02-20 11:03
 **/
public class YieldTest {

    public static void main(String[] args) {
        Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println("子线程A获取到object锁,yield时释放");
                Thread.yield();
                System.out.println("子线程A执行完毕,yield结束,再次获取到对象锁后继续执行");
            }
        }).start();

        new Thread(() -> {
            synchronized (object) {
                System.out.println("子线程B获取到object锁,yield时释放");
                Thread.yield();
                System.out.println("子线程B执行完毕,yield结束,再次获取到对象锁后继续执行");
            }
        }).start();
    }
}
