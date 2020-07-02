package cn.dyg.threadpool.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MyFactory 类是 生产者线程工厂实现类
 *
 * @author dongyinggang
 * @date 2020-06-29 13:59
 **/
public class MyFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public MyFactory(String poolNamePrefix, String threadNamePrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        //判断入参的两个前缀是否为空,为空则使用默认前缀
        if (threadNamePrefix == null || threadNamePrefix.isEmpty()) {
            threadNamePrefix = "-thread-";
        }
        if (poolNamePrefix == null || poolNamePrefix.isEmpty()) {
            poolNamePrefix = "pool";
        }
        this.namePrefix = poolNamePrefix + "-" +
                poolNumber.getAndIncrement() +
                "-" + threadNamePrefix + "-";

    }


    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        //设置线程名称,所属threadGroup
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        //设置是否守护线程
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        //设置优先级
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
