package com.cruise.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/13
 */
public class GenericDemo {

    public static void main(String[] args) {
        Collection<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        // 编译错误
        //list.add("3");
        Collection temp = list;
        // 编译通过
        temp.add("xx");
        // 可以正确输出，说明泛型只是编译期的强类型约束，不会影响运行期
        System.out.println(list);
    }
}
