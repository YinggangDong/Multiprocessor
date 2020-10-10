package cn.dyg.lambda.defaultmethod;

/**
 * MyInterface 接口是 测试默认方法和静态方法的接口
 *
 * @author dongyinggang
 * @date 2020-10-10 16:52
 **/
public interface MyInterface {

    /**
     * defaultMethod 方法是 接口默认方法
     *
     * @author dongyinggang
     * @date 2020/10/10 16:53
     */
    default void defaultMethod(){
        System.out.println("这是接口的默认方法");
    }

    /**
     * staticMethod 方法是 接口静态方法
     *
     * @author dongyinggang
     * @date 2020/10/10 16:53
     */
    static void staticMethod(){
        System.out.println("这是接口的静态方法");
    }
    /**
     * ordinaryMethod 方法是 普通方法
     *
     * @author dongyinggang
     * @date 2020/10/10 16:54
     */
    void ordinaryMethod();
}
