package cn.dyg.keyword.syn;

import cn.dyg.util.ThreadUtil;

import java.util.concurrent.ExecutorService;

/**
 * SynClass 类是 锁一个类
 *
 * @author dongyinggang
 * @date 2021-02-22 09:01
 **/
public class SynClass {

    public static void main(String[] args) {
        SynClass synClass1 = new SynClass();
        SynClass synClass2 = new SynClass();
        //创建一个线程池
        ExecutorService executorService = ThreadUtil.buildThreadExecutor(2);
        //始终是一个线程在执行,sleep时不释放锁,所以始终是一个线程在执行
        executorService.execute(synClass1::synClass);
        executorService.execute(synClass2::synClass);
        //执行完毕后,终止线程池,如果不执行,则进程不会结束
        executorService.shutdown();
        System.out.println("主线程执行完毕");
    }

    /**
     * synClass 方法是 使用synchronized同步一个类
     * 作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
     * 某个线程竞争到类锁后，其他线程就不能执行了，必须等待目标线程释放
     *
     * @author dongyinggang
     * @date 2021/2/22 9:54
     */
    private void synClass() {
        synchronized (SynClass.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synClass 运行中");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "当前线程执行完毕");
        }
    }

}
