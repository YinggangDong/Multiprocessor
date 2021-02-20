package cn.dyg.threadbasic.lifecycle;

/**
 * SleepTest 类是 sleep方法测试
 *
 * @author dongyinggang
 * @date 2021-02-20 08:57
 **/
public class SleepTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("sub is running");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("子线程状态" + t.getState());
        t.start();
        int i = 0;
        int time = 3;
        while (i < time) {
            i++;
            System.out.println("子线程状态" + t.getState());
            Thread.sleep(100);
        }
    }
}
