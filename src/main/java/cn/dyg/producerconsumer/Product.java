package cn.dyg.producerconsumer;

/**
 * Product 类是 产品
 *
 * @author dongyinggang
 * @date 2020-07-05 16:20
 **/
public class Product {

    private int id;

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
