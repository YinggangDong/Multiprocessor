package cn.dyg.threadbasic.lifecycle;

/**
 * YieldTest 类是 yield方法测试
 * 向调度程序发出的提示，表明当前线程愿意放弃使用当前的处理器资源。调度程序可以忽略这个提示。
 * yield是一种启发式的改进线程之间的相对进程的尝试，否则会过度使用CPU。
 * 它的使用应该与详细的分析和基准测试相结合，以确保它实际具有预期的效果。
 * 很少适合使用这种方法。对于调试或测试目的，它可能很有用，因为它可以帮助重现由于竞态条件而产生的错误；
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
