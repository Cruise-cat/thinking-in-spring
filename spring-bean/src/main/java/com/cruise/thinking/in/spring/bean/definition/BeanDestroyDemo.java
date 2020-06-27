package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import com.cruise.thinking.in.spring.bean.definition.factory.UserFactory;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.Student;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 销毁 Bean 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class BeanDestroyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 使用AbstractBeanDefinition#setDestroyMethodName自定义销毁方法
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Student.class)
                .setDestroyMethodName("destroyStudent").getBeanDefinition();
        context.registerBeanDefinition("student",beanDefinition);

        context.register(BeanDestroyDemo.class);

        context.refresh();
        System.out.println("Spring上下文启动了");
        UserFactory bean = context.getBean(UserFactory.class);
        System.out.println(bean);
        System.out.println("Spring上下文准备关闭");
        context.close();
        System.out.println("Spring上下文关闭了");
    }

    @Bean(destroyMethod = "myDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
