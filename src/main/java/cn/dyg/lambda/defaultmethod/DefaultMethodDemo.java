package cn.dyg.lambda.defaultmethod;

/**
 * DefaultMethodDemo 类是接口默认方法demo类
 *
 * @author dongyinggang
 * @date 2020-10-10 16:58
 **/
public class DefaultMethodDemo implements MyInterface{

    public static void main(String[] args) {
        DefaultMethodDemo defaultMethodDemo = new DefaultMethodDemo();
        defaultMethodDemo.defaultMethod();
        MyInterface.staticMethod();
        defaultMethodDemo.ordinaryMethod();
    }

    /**
     * ordinaryMethod 方法是 普通方法
     *
     * @author dongyinggang
     * @date 2020/10/10 16:54
     */
    @Override
    public void ordinaryMethod() {
        System.out.println(this.getClass().getSimpleName()+"实现的ordinaryMethod");
    }
}
