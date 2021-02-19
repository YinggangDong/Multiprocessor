package cn.dyg.threadbasic.lifecycle;

/**
 * StopTest 类是 stop方法测试类
 *
 * @author dongyinggang
 * @date 2021-02-19 19:01
 **/
public class StopTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()-> {
            while(true){
                System.out.println(Thread.currentThread().getName()+" is running ... ");
            }
        });
        t.start();
        Thread.sleep(1000);
        t.stop();
    }
}
