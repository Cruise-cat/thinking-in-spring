package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 外部的单体对象注册实例
 * <p>注册的外部的单体对象的生命周期不会被 Spring 托管</p>
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 * @see SingletonBeanRegistry
 */
public class SingletonBeanRegistryDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // AnnotationConfigApplicationContext 就是 SingletonBeanRegistry 类型
        beanFactory.registerSingleton("admin",new User());

        User user = context.getBean("user", User.class);
        System.out.println(user);

        context.close();
    }
}
