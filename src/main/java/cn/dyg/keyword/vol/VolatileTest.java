package cn.dyg.keyword.vol;

import cn.dyg.util.ThreadUtil;

import java.util.concurrent.ExecutorService;

/**
 * VolatileTest 类是 关键字 volatile 的相关测试
 *
 * @author dongyinggang
 * @date 2021-02-25 16:56
 **/
public class VolatileTest {

    private volatile int volObj = 0;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        ExecutorService executorService = ThreadUtil.buildThreadExecutor(2);
        for (int i = 0; i < 20; i++) {
            executorService.execute(volatileTest::increase);
        }

        Thread.sleep(3000);
        //结果极大可能小于200000
        System.out.println(volatileTest.volObj);
        executorService.shutdown();

    }

    /**
     * increase 方法是 对volatile变量进行一万次++操作
     *
     * @author dongyinggang
     * @date 2021/3/1 17:54
     */
    private void increase() {
        for (int i = 0; i < 10000; i++) {
            volObj++;
        }
        System.out.println("线程结束");
    }


}
