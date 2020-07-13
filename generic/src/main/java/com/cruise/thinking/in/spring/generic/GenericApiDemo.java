package com.cruise.thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java 泛型 API 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/13
 */
public class GenericApiDemo {

    public static void main(String[] args) {
        // 原生类型 primitive types : int long float
        Class<Integer> integerClass = int.class;
        // 数组类型 array types : int[],Object[]
        Class<Object[]> objectClass = Object[].class;
        // 原始类型 raw types : java.lang.String
        Class<String> stringClass = String.class;
        // 泛型参数类型 parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        // class java.util.AbstractList
        System.out.println(parameterizedType.getRawType());
        // 泛型类型变量 Type Variable:
        Type[] types = parameterizedType.getActualTypeArguments();
        // <E>
        Stream.of(types).map(TypeVariable.class::cast).forEach(System.out::println);
    }
}
