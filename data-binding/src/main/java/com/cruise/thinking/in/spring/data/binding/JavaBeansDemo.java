package com.cruise.thinking.in.spring.data.binding;

import com.cruise.thinking.in.spring.data.binding.domain.Employee;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * JavaBeans 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/11
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        // stopClass -> 截止类
        BeanInfo beanInfo = Introspector.getBeanInfo(Employee.class,Object.class);

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            //propertyDescriptor.getReadMethod();
            //propertyDescriptor.getWriteMethod();
            System.out.println(propertyDescriptor);
        });


        System.out.println("===============");
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);

    }
}
