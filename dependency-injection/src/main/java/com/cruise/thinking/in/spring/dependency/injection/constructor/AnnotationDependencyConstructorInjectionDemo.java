package com.cruise.thinking.in.spring.dependency.injection.constructor;

import com.cruise.thinking.in.spring.dependency.injection.holder.UserHolder;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解手动 Constructor 注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotationDependencyConstructorInjectionDemo.class);

        // 使用注解的方式启动Spring上下文也可以使用 Xml的特性
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        UserHolder userHolder = context.getBean(UserHolder.class);

        System.out.println(userHolder);

        context.close();

    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
