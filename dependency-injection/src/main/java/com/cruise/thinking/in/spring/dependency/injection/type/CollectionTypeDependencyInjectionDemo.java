package com.cruise.thinking.in.spring.dependency.injection.type;

import com.cruise.thinking.in.spring.dependency.injection.holder.MultiCityHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 集合类型注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/06/27
 */
public class CollectionTypeDependencyInjectionDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/collection-type-dependency-injection.xml");

        MultiCityHolder cityHolder = context.getBean(MultiCityHolder.class);

        System.out.println(cityHolder);

        context.close();
    }
}
