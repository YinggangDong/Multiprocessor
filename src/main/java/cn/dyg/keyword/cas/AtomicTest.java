package cn.dyg.keyword.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicTest 类是 测试原子类
 *
 * @author dongyinggang
 * @date 2020-07-07 22:50
 **/
public class AtomicTest {

    public static void main(String[] args) {

        //原子类,称为无锁或自旋锁
        AtomicInteger i = new AtomicInteger();

        i.incrementAndGet();
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1, 1);
    }
}
