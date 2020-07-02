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

class Producer implements Runnable {

    private volatile SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产第" + i + "个产品");
            container.push(new Product(i));
        }
    }
}

class Consumer implements Runnable {

    private volatile SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    /**
     * 消费
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("-----消费第" + container.pop().id + "个产品");
        }
    }
}

/**
 * 产品
 */
class Product {

    int id;

    public Product(int id) {
        this.id = id;
    }
}

/**
 * Container 类（或接口）是 缓冲区
 *
 * @author dongyinggang
 * @date 2020/7/2
 */
class SynContainer {

    /**
     * 定义缓冲区大小
     */
    Product[] products = new Product[10];

    /**
     * 容器计数器
     */
    int count = 0;

    /**
     * 生产者放入产品
     */
    public synchronized void push(Product product) {
        //如果容器满了,则生产者暂停生产,等待消费者消费
        if (count == products.length) {
            //通知消费者消费,生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果没有满,则向其中放入产品
        products[count] = product;
        count++;

        //可以通知消费者消费
        this.notifyAll();
    }

    /**
     * 消费者消费产品
     */
    public synchronized Product pop() {

        //判断是否存在产品供消费
        if (count == 0) {
            //不存在则等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //存在则可以进行消费
        count--;
        Product product = products[count];

        //消费完成,通知生产者生产
        this.notifyAll();

        return product;
    }

}
