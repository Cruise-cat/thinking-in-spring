package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 基于注解的方式注册{@link BeanDefinition}和基于 Java API 的方式注册{@link BeanDefinition}
 *
 * @author Cruise
 * @version 1.0
 * @see Import
 * @see Bean
 * @see Component
 * @since 2020/6/26
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 配置类方式注册Bean
        context.register(AnnotationBeanDefinitionDemo.class);

        context.refresh();

        // Java API 命名方式
        registerUserBeanDefinition(context, "specialUser");
        // Java API 非命名方式，使用类的全限定名
        registerUserBeanDefinition(context);

        System.out.println("Config Beans:" + context.getBeansOfType(Config.class));
        System.out.println("User Beans:" + context.getBeansOfType(User.class));

        // 获取别名
        String[] users = context.getAliases("user");
        System.out.println(Arrays.toString(users));

        // 显示关闭Spring上下文
        context.close();
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        beanDefinitionBuilder
                .addPropertyValue("id", 2)
                .addPropertyValue("name", "Tom");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }

    @Component
    public static class Config {

        /**
         * user 是 id,cruise 是别名
         *
         * @return
         */
        @Bean({"user", "cruise"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("Cruise");
            return user;
        }
    }
}

