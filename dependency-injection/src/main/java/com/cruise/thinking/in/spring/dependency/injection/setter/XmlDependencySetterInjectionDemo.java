package com.cruise.thinking.in.spring.dependency.injection.setter;

import com.cruise.thinking.in.spring.dependency.injection.holder.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 xml 资源的手动 Setter 注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("classpath:/META-INF/dependency-injection-setter.xml");

        UserHolder bean = beanFactory.getBean(UserHolder.class);

        System.out.println(bean);

    }
}
