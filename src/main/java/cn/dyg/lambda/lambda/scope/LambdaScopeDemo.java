package cn.dyg.lambda.lambda.scope;

import java.util.function.IntConsumer;

/**
 * LambdaScopeDemo 类是 lambda表达式作用域测试
 *
 * @author dongyinggang
 * @date 2020-10-13 08:38
 **/
public class LambdaScopeDemo {
    /**
     * 静态变量
     */
    private static int staticNum = 1;
    /**
     * 对象字段
     */
    private int objectNum = 2;

    public static void main(String[] args) {

        LambdaScopeDemo lambdaScopeDemo = new LambdaScopeDemo();

        //1.访问局部变量
        lambdaScopeDemo.localNumTest();

        //2.访问对象字段与静态变量
        lambdaScopeDemo.objectAndStaticTest();
        //3.不能访问接口的默认方法

        //4.Lambda表达式中的this

    }


    /**
     * localNumTest 方法是 1.lambda访问局部变量
     * 对于局部变量，lambda可读，不可写，即可以使用隐性的具有final语义的局部变量
     *
     * @author dongyinggang
     * @date 2020/10/13 19:41
     */
    private void localNumTest() {
        //IntConsumer -以int作为输入，执行某种动作，没有返回值

        System.out.println("1.访问局部变量：");
        //1.1 可以直接在lambda表达式中访问外层的局部变量
        //localNum可以显式的声明为final,也可以不声明,但要求其实际不可变
        int localNum = 1;
        IntConsumer localNumOperation = (a) -> {
            //编译出错
//            localNum++;
            System.out.println("局部变量值为：" + (localNum));
        };
        localNumOperation.accept(0);
        //localNum++;不合法,如果localNum值改变,编译报错,提示lambda表达式中使用的变量应当是final的
//        localNum++;

        //1.2 在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。编译报错
//        IntConsumer localNumSameName = (localNum)-> System.out.println("参数与局部变量重名");
    }

    /**
     * objectAndStaticTest 方法是 2.访问对象字段与静态变量
     *  和局部变量不同的是，Lambda内部对于实例的字段（即：成员变量）以及静态变量是即可读又可写。
     *
     * @author dongyinggang
     * @date 2020/10/13 19:41
     */
    private void objectAndStaticTest() {
        System.out.println("2.访问对象字段与静态变量：");
        //访问静态变量staticNum和对象字段objectNum
        IntConsumer staticNumOperation =
                (a) -> System.out.println("对象字段值为：" + objectNum + "静态变量值为：" + staticNum);
        staticNumOperation.accept(0);
        //可读可写
        staticNum++;
        objectNum++;
        staticNumOperation.accept(0);
    }
}
