package com.cruise.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Spring Bean Class 加载阶段解析
 * <ol>
 *     <li>方法入口{@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}</li>
 *     <li>代码：Class<?> resolvedClass = resolveBeanClass(mbd, beanName)</li>
 *     <li>先判断 {@link BeanDefinition}的 beanClass属性是不是Class类型，第一次解析时是String类型 </li>
 *     <li>执行{@link AbstractBeanFactory#doResolveBeanClass(RootBeanDefinition, Class[])}方法 </li>
 *     <li>执行{@link AbstractBeanDefinition#resolveBeanClass(ClassLoader)}方法</li>
 *     <li>通过反射的方式将{@link BeanDefinition}的 beanClass属性从String类型变为Class类型</li>
 * </ol>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/28
 */
public class BeanClassLoaderDemo {
}
