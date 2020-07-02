package cn.dyg.producerconsumer.blockingqueue.simple;

/**
 * Product 类是 产品类
 *
 * @author dongyinggang
 * @date 2020-06-29 21:12
 **/
public class Product {


    /**
     * 产品编号
     */
    private int id;

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
