package com.cruise.thinking.in.spring.dependency.injection.type;

import com.cruise.thinking.in.spring.dependency.injection.holder.CityHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基础类型依赖注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class BaseTypeDependencyInjectionDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/base-type-dependency-injection.xml");

        CityHolder cityHolder = context.getBean(CityHolder.class);

        System.out.println(cityHolder);

        context.close();
    }
}
