package cn.dyg.keyword.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * ObjectSize 类是 估算对象大小
 *
 * @author dongyinggang
 * @date 2021-02-23 15:56
 **/
public class ObjectSize {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }
}


class A {
    private long l;
}

class B {
    private double s;
    private int i;

}