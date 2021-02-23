package cn.dyg.keyword.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * JavaObjectLayoutTest 类是 jol java对象
 *
 * @author dongyinggang
 * @date 2020-07-07 15:43
 **/
public class JavaObjectLayoutTest {

    public static void main(String[] args) {
        testLockFlag();
    }

    /**
     * testLockFlag 方法是 测试加锁对对象头的影响
     *
     * @author dongyinggang
     * @date 2021/2/23 15:45
     */
    private static void testLockFlag() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}

