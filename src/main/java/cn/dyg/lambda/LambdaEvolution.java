package cn.dyg.lambda;

/**
 * LambdaEvolution 类是 lambda表达式演进过程类
 *
 * @author dongyinggang
 * @date 2020-09-04 11:30
 **/
public class LambdaEvolution {

    /**
     * 2.静态内部类
     * 在内部定义的静态类
     */
    static class StaticInnerClass implements FunctionalInterface {

        @Override
        public void lambda() {
            System.out.println("这是一个静态内部类");
        }
    }

    public static void main(String[] args) {

        //1.普通类
        FunctionalInterface ordinaryClass = new OrdinaryClass();
        ordinaryClass.lambda();

        //2.静态内部类
        FunctionalInterface staticInnerClass = new StaticInnerClass();
        staticInnerClass.lambda();

        /**
         * 3.局部内部类
         */
        class LocalInnerClass implements FunctionalInterface {

            @Override
            public void lambda() {
                System.out.println("这是一个局部内部类");
            }
        }

        FunctionalInterface localInnerClass = new LocalInnerClass();
        localInnerClass.lambda();

        /**
         * 4.匿名内部类 AnonymousInnerClass
         */
        FunctionalInterface anonymousInnerClass = new FunctionalInterface() {

            @Override
            public void lambda() {
                System.out.println("这是一个匿名内部类");
            }
        };
        anonymousInnerClass.lambda();

        /**
         * 5.lambda简化的匿名内部类
         */
        FunctionalInterface lambdaClass =
                () -> System.out.println("这是一个lambda简化的匿名内部类");
        lambdaClass.lambda();

    }
}

/**
 * 函数式接口
 * 任何接口，如果只包含唯一一个抽象方法，就是一个函数式接口，例： Runnable（）
 */
interface FunctionalInterface {
    /**
     * lambda 方法是 唯一接口
     *
     * @author dongyinggang
     * @date 2020/9/4 18:00
     */
    void lambda();
}

/**
 * 1.普通类，最原始的类表现
 */
class OrdinaryClass implements FunctionalInterface {

    @Override
    public void lambda() {
        System.out.println("这是一个普通类");
    }
}


