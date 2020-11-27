package cn.dyg.keyword.syn;

/**
 * SynTest 类是 synchronized 锁
 * synchronized 是 Java 的一个关键字，它能够将代码块(方法)锁起来
 *
 * @author dongyinggang
 * @date 2020-11-27 14:16
 **/
public class SynTest {

    /**
     * test 方法是 给方法加synchronized锁
     *
     * @param
     * @return
     * @author dongyinggang
     * @date 2020/11/27 16:28
     */
    public synchronized void test(){
        //doSomething
    }

    /**
     * test2 方法是 给代码块加synchronized锁
     *
     * @param
     * @return
     * @author dongyinggang
     * @date 2020/11/27 16:39
     */
    public void test2(){
        // 修饰代码块
        synchronized (this){
            //doSomething
        }
    }

}
