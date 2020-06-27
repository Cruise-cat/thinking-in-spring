package com.cruise.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException}示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(NoUniqueBeanDefinitionExceptionDemo.class);

        try {
            context.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("String 类型的 Bean 存在 [%s] 个,具体信息：%s \n",
                    e.getNumberOfBeansFound(),e.getMessage());
        }

        context.close();
    }

    @Bean
    public String bean1(){
        return "bean1";
    }

    @Bean
    public String bean2(){
        return "bean2";
    }
}
