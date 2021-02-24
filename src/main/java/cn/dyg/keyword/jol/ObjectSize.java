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
    /**
     * long对象 8个字节
     */
    private long l;
}

class B {

    /**
     * 1个字节,会补对应字节
     */
    private boolean flag;
    /**
     * 调整后,会作为对象头12个字节后的4个字节
     */
    private int i;
    /**
     * string只有引用，若开启指针压缩会占用4个字节，否则占用8个字节
     */
    private String j;
    /**
     * 只有A的引用a,若开启指针压缩会占用4个字节，否则占用8个字节
     */
    private A a;
}