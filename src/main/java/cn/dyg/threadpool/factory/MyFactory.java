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

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
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
                POOL_NUMBER.getAndIncrement() +
                "-" + threadNamePrefix + "-";

    }


    /**
     * newThread 方法是 线程创建方法
     *
     * @param r runnable对象
     * @return 线程
     * @author dongyinggang
     * @date 2021/1/19 19:58
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
