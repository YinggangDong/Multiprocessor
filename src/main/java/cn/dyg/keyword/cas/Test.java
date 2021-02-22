package cn.dyg.keyword.cas;

/**
 * Test 类是 测试父线程和子线程是否能够共享变量。
 * 结论：
 * 当主线程和子线程共享的变量是非基础类型的变量时,可以在并行情况下通过锁保证子线程和主线程的一致
 * 若为基础类型变量，则无法进行共享
 *
 * @author dongyinggang
 * @date 2020-07-08 18:27
 **/
public class Test {

    public static void main(String[] args) {
        Integer a = 1;
        //1.创建一个子线程,并且start该线程
        SubThread subThread = new SubThread(a);
        new Thread(subThread).start();
        new Thread(subThread).start();
        //2.线程sleep 0.1s,查看主线程的a值是否变化——不变化
        try {
            Thread.sleep(100);
            System.out.println("主线程的a："+a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
