package cn.dyg.threadbasic.source;

/**
 * ThreadSourceTest 类是 Thread 类常用方法解析
 *
 * @author dongyinggang
 * @date 2020-11-30 10:46
 **/
public class ThreadSourceTest {

    public static void main(String[] args) {
        noArgsConstructor();
    }

    /**
     * noArgsConstructor 方法是 无参构造
     * 基本无意义
     *
     * @author dongyinggang
     * @date 2020/11/30 15:17
     */
    public static void noArgsConstructor(){
        Thread thread = new Thread();
        System.out.println(thread.getName());
        thread.start();
    }
}
