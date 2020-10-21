package cn.dyg.lambda.methodref;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Constructor 类是 构造函数的方法引用形式
 *
 * @author dongyinggang
 * @date 2020-10-20 18:49
 **/
public class Constructor {

    /**
     * constructor 方法是 调用构造函数
     *
     * @author dongyinggang
     * @date 2020/10/10 15:14
     */
    static void constructor(List<Person> roster){
        System.out.println("4.调用构造函数：");
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

    /**
     * transferElements 方法是 将一个collection的元素放到新的collection中去
     *
     * @param sourceCollection 源集合
     * @param collectionFactory 集合工厂,重写get()方法的Supplier接口实现类
     * @return DEST的集合对象
     * @author dongyinggang
     * @date 2020/10/20 18:52
     */
    private static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(SOURCE sourceCollection,Supplier<DEST> collectionFactory) {
        //通过get方法创建一个新的集合对象
        DEST result = collectionFactory.get();
        //将源集合中的元素add进新的集合对象中
        result.addAll(sourceCollection);
        //将新的集合对象放回
        return result;
    }
}
