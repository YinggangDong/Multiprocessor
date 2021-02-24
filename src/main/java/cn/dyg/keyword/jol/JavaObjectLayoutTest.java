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
        //锁对对象头信息的变更
//        testLockFlag();
        //数组对象头信息
        arrayObjectHeader();
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

    /**
     * arrayObjectHeader 方法是 数组的对象头信息
     *
     * @author dongyinggang
     * @date 2021/2/24 8:24
     */
    private static void arrayObjectHeader() {
        String[] arrays = new String[258];
        arrays[0] = "hello world";
        System.out.println(ClassLayout.parseInstance(arrays).toPrintable());
    }
}

