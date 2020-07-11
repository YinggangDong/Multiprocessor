package cn.dyg.keyword.cas;

/**
 * Test 类是 测试父线程和子线程是否能够共享变量。
 *
 * @author dongyinggang
 * @date 2020-07-08 18:27
 **/
public class Test {

    static volatile Integer a = 1;
    public static void main(String[] args) {

        subThread subThread = new subThread(a);
        new Thread(subThread).start();

        try {
            Thread.sleep(5000);
            System.out.println(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(subThread).start();
    }
}
