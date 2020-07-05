package cn.dyg.producerconsumer.selfqueue;

/**
 * Container 类（或接口）是 自定义缓冲区
 *
 * @author dongyinggang
 * @date 2020/7/2
 */
public class SynContainer {

    /**
     * 定义缓冲区大小
     */
    private Product[] products = new Product[10];

    /**
     * 容器计数器
     */
    private volatile int count = 0;

    /**
     * 生产者放入产品
     */
    public synchronized void push(Product product) {
        //如果容器满了,则生产者暂停生产,等待消费者消费
        while (count == products.length) {
            //通知消费者消费,生产者等待
            System.out.println("缓冲区已满，生产者暂停生产...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
                让渡cpu,唤醒消费者线程消费,告知准备竞争锁
                 this.notifyAll();（此处notifyAll无效,notify或notifyAll方法不会释放锁，
                而是在当前加锁代码块执行完成后释放锁）
             */
        }

        //如果没有满,则向其中放入产品
        products[count] = product;
        count++;

        System.out.println("生产第" + product.id + "个产品");
        //可以通知消费者消费
        this.notifyAll();
    }

    /**
     * 消费者消费产品
     */
    public synchronized void pop() {

        //判断是否存在产品供消费
        while (count == 0) {
            //不存在则等待生产者生产
            System.out.println("无产品可消费，请等待...");

            try {
                //wait进入阻塞的线程必须通过notify/notifyAll才能唤醒
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
                唤醒生产者线程,告知准备竞争锁
                this.notifyAll();（此处notifyAll无效,notify或notifyAll方法不会释放锁，
                而是在当前加锁代码块执行完成后释放锁）
             */
        }

        //存在则可以进行消费.count-1 即为被消费的产品的下标
        count--;
        Product product = products[count];
        System.out.println("-----消费第" + product.id + "个产品");
        //消费完成,通知生产者生产
        this.notifyAll();
    }

}
