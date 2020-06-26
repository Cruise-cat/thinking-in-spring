package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");
        // 使用静态方法实例化Bean
        User userByStaticMethod = context.getBean("user-by-static-method", User.class);
        System.out.println(userByStaticMethod);

        // 使用实例方法实例化Bean
        User userByInstanceMethod = context.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);
        System.out.println(userByStaticMethod == userByInstanceMethod);//false

        // FactoryBean实例化Bean
        User userByFactoryBean1 = context.getBean("user-by-factory-bean", User.class);
        System.out.println(userByFactoryBean1);
        User userByFactoryBean2 = context.getBean("user-by-factory-bean", User.class);
        // 如果是单例返回true 如果是原型返回false
        System.out.println(userByFactoryBean1 == userByFactoryBean2);
    }
}
