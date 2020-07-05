package cn.dyg.producerconsumer.selfqueue;

/**
 * TestSelfQueue 类是 缓冲区实现生产着消费者模式（管程法）
 *
 * @author dongyinggang
 * @date 2020-07-02 14:17
 **/
public class TestSelfQueue {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Thread(new Producer(container)).start();
        new Thread(new Consumer(container)).start();

    }
}
