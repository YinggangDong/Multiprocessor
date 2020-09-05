package cn.dyg.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * ListOperation 类是 lambda对list的操作
 *
 * @author dongyinggang
 * @date 2020-09-05 09:52
 **/
public class ListOperation {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }

        //原始foreach循环
        for (String s : list) {
            System.out.println(s);
        }

        //使用 lambda 表达式以及函数操作(functional operation)
        list.forEach((s)-> System.out.println(s));


        //在 Java 8 中使用双冒号操作符(double colon operator)
        list.forEach(System.out::println);

    }
}
