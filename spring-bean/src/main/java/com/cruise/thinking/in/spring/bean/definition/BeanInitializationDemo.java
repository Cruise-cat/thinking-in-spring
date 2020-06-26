package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import com.cruise.thinking.in.spring.bean.definition.factory.UserFactory;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.Student;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 初始化示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitializationDemo.class);

        // 使用AbstractBeanDefinition#setInitMethodName自定义初始化方法
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Student.class)
                .setInitMethodName("initStudent").getBeanDefinition();
        context.registerBeanDefinition("student",beanDefinition);

        context.refresh();
        System.out.println("Spring上下文启动了");
        context.close();
    }

    @Bean(initMethod = "myInit")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
