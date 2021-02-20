package cn.dyg.threadbasic.create;

import java.util.concurrent.Callable;

/**
 * CallableThread 类是 通过实现callable接口创建线程
 *
 * @author dongyinggang
 * @date 2020-11-30 08:43
 **/
public class CallableThread implements Callable<Integer> {

    /**
     * call 方法是 重写接口的call方法
     * 和实现Runnable的区别：
     * 1.方法可以有返回值
     * 2.可以抛出异常
     * 3.启动实现 Callable 接口的线程,需要有 FutureTask 实现类的支持，用于接收运算结果。FutureTask 是 Future 接口的实现类
     *
     * @return return的类型是根据 实现的 Callable 接口的 <T> 来决定的，若不指定的话,则返回值为object
     * @author dongyinggang
     * @date 2020/11/30 8:44
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum += 1;
        }
        return sum;
    }
}
