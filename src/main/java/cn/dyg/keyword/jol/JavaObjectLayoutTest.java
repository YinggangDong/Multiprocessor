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
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }


        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }
}

class A{
    private long l;
}

class B{
    private double s;
    private int i;

}
