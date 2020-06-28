package com.cruise.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

import java.util.Iterator;

/**
 * 面向注解 {@link BeanDefinition} 解析示例
 * <p>
 * 问题1：为什么{@link AnnotatedBeanDefinitionReader}没有实现{@link BeanDefinitionReader}?
 * </p>
 * <p>
 * 因为{@link BeanDefinitionReader}?是面向资源的，资源可以是本地的一个文件也可以是HTTP管道的文件</br>
 * 而{@link AnnotatedBeanDefinitionReader}是基于注解的实现，是不同的方式
 * </p>
 * <p>
 * 问题2：注册BeanDefinition是如何保证注册的顺序的？
 * </p>
 * <p>
 * {@link DefaultListableBeanFactory}中使用beanDefinitionMap（ConcurrentHashMap类型）存储BeanDefinition，
 * 使用beanDefinitionNames(ArrayList)保证BeanDefinition的注册顺序
 * </p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/23
 */
public class AnnotatedBeanDefinitionParsingDemo {

    /**
     * @param args
     * @see AnnotationBeanNameGenerator id生成器
     */
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

        int countBefore = beanFactory.getBeanDefinitionCount();
        reader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int countAfter = beanFactory.getBeanDefinitionCount();
        System.out.printf("注册前%s个BeanDefinition，注册后%s个BeanDefinition%n", countBefore, countAfter);

        // 打印BeanFactory中的Bean名称
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        while (beanNamesIterator.hasNext()) System.out.println(beanNamesIterator.next());

        AnnotatedBeanDefinitionParsingDemo bean = beanFactory.getBean("annotatedBeanDefinitionParsingDemo", AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(bean);
    }
}
