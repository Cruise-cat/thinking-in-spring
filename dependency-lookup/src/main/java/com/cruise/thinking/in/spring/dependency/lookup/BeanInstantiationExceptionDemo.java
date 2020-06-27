package com.cruise.thinking.in.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);

        context.registerBeanDefinition("errorBean",beanDefinitionBuilder.getBeanDefinition());

        // 启动时抛出异常
        context.refresh();

        context.close();
    }
}
