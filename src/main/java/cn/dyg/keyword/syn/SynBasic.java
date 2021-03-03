package cn.dyg.keyword.syn;

/**
 * SynBasic 类是 synchronized 基础类
 *
 * @author dongyinggang
 * @date 2021-03-03 11:04
 **/
public class SynBasic {

    /**
     * test 方法是 测试 synchronized 语义
     *
     * @author dongyinggang
     * @date 2021/3/3 11:06
     */
    public void test() {
        synchronized (SynBasic.class) {
            System.out.println("just test synchronized");
        }
    }
}
