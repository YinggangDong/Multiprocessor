package cn.dyg.lambda.defaultmethod;

/**
 * DefaultMethodDemo 类是接口默认方法demo类
 *
 * 参考内容：
 * 1.Java8 在接口的变化：
 *      https://blog.csdn.net/axuanqq/article/details/82773631
 * 2.接口默认方法
 *      https://blog.csdn.net/h294590501/article/details/80303722
 * 3.JAVA8学习5-接口默认方法（default）
 *      https://blog.csdn.net/z_yemu/article/details/89312788?utm_medium=distribute.pc_relevant.none-task-blog-title-4&spm=1001.2101.3001.4242
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
