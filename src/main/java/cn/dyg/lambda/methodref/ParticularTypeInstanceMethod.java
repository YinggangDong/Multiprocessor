package cn.dyg.lambda.methodref;

import java.util.Arrays;

/**
 * ParticularTypeInstanceMethod 类是 特定类型任意对象的实例方法的方法引用
 *
 * @author dongyinggang
 * @date 2020-10-20 18:47
 **/
public class ParticularTypeInstanceMethod {

    /**
     * particularTypeInstanceMethod 方法是 调用特定类型任意对象的实例方法
     *
     * @author dongyinggang
     * @date 2020/10/10 11:29
     */
    static void particularTypeInstanceMethod(Person[] rosterAsArray) {
        System.out.println("3.调用特定类型任意对象的实例方法:");
        //官方文档示例
        String[] stringArray = {"Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        /**
         * 刚开始考虑调用的方法是 compareByAgeNonStatic(Person a, Person b)
         * 有两个参数，结果发现会出现编译错误，提示在静态上下文中调用了非静态变量
         *
         * 看了一下官方文档中使用的compareToIgnoreCase方法，发现其只有一个入参，
         * 然后就给 compareByAgeNonStatic 写了一个只有一个参数的重载方法，发现可以正常调用
         *
         * 虽然官方文档说着可以调用特定类型任意对象的实例方法，但实际是有限制的，并不能像静态方法一样，
         * 可以有多个入参
         *
         * 疑问：
         * 1. 这里感觉理解有问题，Arrays.sort的比较器调用方法为静态时可以有两个参数的原因
         * 实际是因为作为静态方法，他不需要通过类的实例调用，可以通过类直接进行调用，而实例方法必须通过实例调用，
         * 因此形式为一个对象调用来和另一个对象比较
         *
         */
        //这里使用了类的名称，而不是具体的对象，尽管指定的是实例方法。
        // 使用这种形式时，函数式接口的第一个参数匹配调用对象，第二个参数匹配方法指定的参数。
        Arrays.sort(rosterAsArray, Person::compareByAgeNonStatic);
        //和上方等价
        Arrays.sort(rosterAsArray, (a, b) -> a.compareByAgeNonStatic(b));
        //如果调用 compareByAgeNonStatic(a,b) 实际是调用特定对象的实例方法,此时两个参数都是方法参数
        Arrays.sort(rosterAsArray, (a, b) -> new Person().compareByAgeNonStatic(a, b));
        System.out.println("排序完成：");
        for (Person person : rosterAsArray) {
            person.printPerson();
        }
    }

}
