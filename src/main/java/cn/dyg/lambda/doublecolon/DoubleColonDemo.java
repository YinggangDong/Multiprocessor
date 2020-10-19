package cn.dyg.lambda.doublecolon;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * DoubleColonDemo 类是 双冒号应用demo
 *
 * “::” 是域操作符（也可以称作定界符、分隔符）。
 *
 * ::关键字提供了四种语法，可以直接引用已有Java类或对象（实例）的方法或构造器。
 * 与lambda联合使用，::关键字可以使语言更简洁，减少冗余代码。
 *
 * 参考内容：
 * 1.JAVA 8 '::' 关键字，带你深入了解它！
 * https://blog.csdn.net/weixin_42740530/article/details/104655470
 * 2.官方文档
 * https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 * 3.Java 8 新特性：Lambda 表达式之方法引用（Lambda 表达式补充版）
 * https://blog.csdn.net/sun_promise/article/details/51190256
 *
 * @author dongyinggang
 * @date 2020-10-09 14:49
 **/
public class DoubleColonDemo {

    public static void main(String[] args) {

        //1.引用静态方法
        staticMethod();

        //2.调用特定对象的实例方法
        particularObjInstanceMethod();

        //3.调用特定类型的任意对象的实例方法
        particularTypeArbitraryObjectInstanceMethod();

        //4.调用构造函数
        constructor();
    }

    /**
     * staticMethod 方法是 通过双冒号 引用静态方法 ContainingClass::staticMethodName
     * <p>
     * 本例是引用Person类的 compareByAge 方法
     *
     * @author dongyinggang
     * @date 2020/10/10 9:43
     */
    private static void staticMethod() {
        System.out.println("1.调用静态方法：");
        List<Person> roster = Person.createRoster();
        for (Person p : roster) {
            p.printPerson();
        }
        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

        /**
         * 年龄比较器类
         *
         * 实现了函数式接口Comparator
         *
         * 注：Comparator接口 有两个抽象方法 compare 和 equals方法
         * 而它被称为函数式接口的原因是：
         *    如果接口声明了一个覆盖java.lang.Object的全局方法之一的抽象方法，
         * 那么它不会计入接口的抽象方法数量中，因为接口的任何实现都将具有java.lang.Object
         * 或其他地方的实现。
         */
        class PersonAgeComparator implements Comparator<Person> {
            @Override
            public int compare(Person a, Person b) {
                //这里如果 参数1 > 参数2 时返回正数,则为升序排列,否则为降序排列
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }

        /**
         * 1.通过局部内部类作为sort方法的比较器实现类
         */
        Arrays.sort(rosterAsArray, new PersonAgeComparator());
        System.out.println("通过局部内部类的方式,排序完成：");
        for (Person person : rosterAsArray) {
            person.printPerson();
        }

        /**
         * 2.通过匿名内部类方式实现
         *
         * 将泛型指定为Person类，并重写compare方法
         * 参数类型变为Person是可行的，因为是Object的子类
         */
        Arrays.sort(rosterAsArray, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Person.compareByAge(o1, o2);
            }
        });

        /**
         * 3. 通过lambda表达式的方式作为Comparator的实现类，替代匿名内部类
         */
        Arrays.sort(rosterAsArray,
                (Person a, Person b) -> a.getBirthday().compareTo(b.getBirthday()));

        /**
         * 4.通过已存在的方法简化lambda表达式
         * 和3的区别只是变成调用已存在方法而非重写比较逻辑
         * 实际还是将通过lambda表达式替代了Comparator的匿名内部类的生成
         */
        Arrays.sort(rosterAsArray, (a, b) -> {
            return Person.compareByAge(a, b);
        });

        /**
         * 和上方等价，因为是一句话方法体，进行格式简化
         */
        Arrays.sort(rosterAsArray, (a, b) -> Person.compareByAge(a, b));


        /**
         * 4.调用已经存在的方法的最简形式
         *
         * 方法引用Person::compareByAge在语义上与lambda表达式相同(a, b) -> Person.compareByAge(a, b)。每个都有以下特征：
         *
         * - 它的形参列表是从复制Comparator<Person>.compare，这里是(Person, Person)。
         * - 它的主体调用该方法Person.compareByAge。
         */
        Arrays.sort(rosterAsArray, Person::compareByAge);

    }

    /**
     * particularObjInstanceMethod 方法是 通过双冒号 调用 特定对象的实例方法
     *
     * @author dongyinggang
     * @date 2020/10/10 10:23
     */
    private static void particularObjInstanceMethod() {
        System.out.println("2.调用特定对象的实例方法:");

        List<Person> roster = Person.createRoster();
        for (Person p : roster) {
            p.printPerson();
        }
        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);
        Person[] rosterAsArray1 = rosterAsArray.clone();

        /**
         * 1.使用Comparator的实现类作为比较器
         *
         * 声明comparator时必须用Comparator<Person> ，不能是Comparator，否则编译期报错
         * wrong 1st argument type  Found Person[] required T[]
         * 意指rosterAsArray参数不满足sort方法的参数类型，默认参数类型是泛型T[] 而非实际类型Person[]
         *
         * 当comparator未指明其泛型的类型时，尽管其使用实现类进行实例化，编译期也不能通过这种隐式的关系推断出
         * 实际类型，因此期望入参是T[]
         *
         * 当比较器的泛型被显式的指明为Person时，这时sort方法会期望两个参数分别为
         * Person[]和Comparator<Person> c
         *
         * 疑问：
         * 1. Arrays.sort方法的参数类型jre推断过程是如何的？是通过比较器的泛型的实际类型来推断么？
         *  -- Java8中对Lambda表达式中方法参数的类型推断（一） ：https://blog.csdn.net/u013096088/article/details/69367260
         */
        Comparator<Person> comparator = new ComparatorImpl();
        Arrays.sort(rosterAsArray1, comparator::compare);
        //作为Comparator的实现类,可以简写为以下形式,上面的方式实际在运行过程中会生成匿名类
        Arrays.sort(rosterAsArray1, comparator);
        /**
         * 2.引用一个普通类的方法作为比较器
         *
         * 通过双冒号引用特定对象 comparatorProvider 的实例方法 compareByAge
         * 如果直接使用 ComparatorProvider::compareByAge 会提示在 static 上下文中调用非静态方法
         */
        ComparatorProvider comparatorProvider = new ComparatorProvider();
        Person[] rosterAsArray2 = rosterAsArray.clone();
        Arrays.sort(rosterAsArray2, comparatorProvider::compareByAge);
        System.out.println("排序完成：");
        for (Person person : rosterAsArray2) {
            person.printPerson();
        }

    }

    /**
     * particularTypeArbitraryObjectInstanceMethod 方法是 调用特定类型任意对象的实例方法
     *
     * @author dongyinggang
     * @date 2020/10/10 11:29
     */
    private static void particularTypeArbitraryObjectInstanceMethod() {
        System.out.println("3.调用特定类型任意对象的实例方法:");
        //官方文档示例
        String[] stringArray = {"Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        List<Person> roster = Person.createRoster();
        for (Person p : roster) {
            p.printPerson();
        }
        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);
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
        Arrays.sort(rosterAsArray, Person::compareByAgeNonStatic);
        //和上方等价
        Arrays.sort(rosterAsArray, (a, b) -> a.compareByAgeNonStatic(b));
        //如果调用 compareByAgeNonStatic(a,b) 实际是调用特定对象的实例方法
        Arrays.sort(rosterAsArray, (a, b) -> new Person().compareByAgeNonStatic(a, b));
        System.out.println("排序完成：");
        for (Person person : rosterAsArray) {
            person.printPerson();
        }
    }

    /**
     * constructor 方法是 调用构造函数
     *
     * @author dongyinggang
     * @date 2020/10/10 15:14
     */
    private static void constructor(){
        System.out.println("4.调用构造函数：");
        List<Person> roster = Person.createRoster();
        for (Person p : roster) {
            p.printPerson();
        }
        //1.通过匿名内部类实现get()方法,调用构造函数
        Set<Person> rosterSet = transferElements(roster, new Supplier<Set>(){
            @Override
            public Set get() {
                return new HashSet<>();
            }
        });
        //2.通过lambda表达式实现get()方法,调用构造函数
        Set<Person> rosterSetLambda = transferElements(roster,()->new HashSet<>());
        //3.简化lambda表达式实现get()方法,调用构造函数
        Set<Person> rosterSimple = transferElements(roster,HashSet::new);
        System.out.println("赋值完成：");
        for (Person person : rosterSimple) {
            person.printPerson();
        }
    }

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(
            SOURCE sourceCollection,
            Supplier<DEST> collectionFactory) {
        DEST result = collectionFactory.get();
        result.addAll(sourceCollection);
        return result;
    }

    public void test(){
        Test teset= Math::pow;
        teset.pow(1,1);
    }

}

/**
 * Comparator的实现类
 */
class ComparatorImpl implements Comparator<Person> {

    @Override
    public int compare(Person a, Person b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}

/**
 * 含compareByAge方法的类
 */
class ComparatorProvider {

    public int compareByAge(Person a, Person b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}

interface Test{
    double pow(int a,int b);
}