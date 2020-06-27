package com.cruise.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);

        context.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动时抛出异常
        context.refresh();

        context.close();
    }

    static class POJO implements InitializingBean {

        @PostConstruct
        public void init() throws Exception {
            throw new Exception("init Exception");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet Exception");
        }
    }
}
