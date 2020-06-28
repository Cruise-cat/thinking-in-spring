package com.cruise.thinking.in.spring.dependency.injection.setter;

import com.cruise.thinking.in.spring.dependency.injection.holder.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 API 的手动 Setter 注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class ApiDependencySetterInjectionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册 UserHolder的 BeanDefinition
        context.registerBeanDefinition("userHolder", createBeanDefinition());

        // 使用注解的方式启动Spring上下文也可以使用 Xml的特性
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        UserHolder userHolder = context.getBean(UserHolder.class);

        System.out.println(userHolder);

        context.close();

    }

    private static BeanDefinition createBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        // Setter 注入是没有顺序的
        beanDefinitionBuilder.addPropertyReference("user", "superUser");

        return beanDefinitionBuilder.getBeanDefinition();
    }

}
