package cn.dyg.keyword.jol;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.List;

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
//        arrayObjectHeader();
        test();
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

    private static void test() {
        WorkOrderPO wr = new WorkOrderPO();
        wr.setWrCode("ZXGD202108210001");
//        System.out.println(GraphLayout.parseInstance("ZXGD202108210001").toPrintable());
//        System.out.println(ObjectSizeCalculator.getObjectSize(wr));
//        System.out.println(GraphLayout.parseInstance(wr).toPrintable());
        List<WorkOrderPO> list = new ArrayList<>();
        list.add(wr);
        System.out.println(ObjectSizeCalculator.getObjectSize(list));
        for (int i = 0; i < 99; i++) {
            WorkOrderPO wr1 = new WorkOrderPO();
            wr1.setWrCode("ZXGD202108240000" + i);
            list.add(wr1);
            if (i == 98) {
                System.out.println(GraphLayout.parseInstance(wr1).toPrintable());
                System.out.println(ObjectSizeCalculator.getObjectSize("ZXGD202108240000"));
                System.out.println(ObjectSizeCalculator.getObjectSize("ZXGD202108240000" + i));
            }
        }
        System.out.println(ObjectSizeCalculator.getObjectSize(list));
//        System.out.println(GraphLayout.parseInstance(list).toPrintable());

    }
}

