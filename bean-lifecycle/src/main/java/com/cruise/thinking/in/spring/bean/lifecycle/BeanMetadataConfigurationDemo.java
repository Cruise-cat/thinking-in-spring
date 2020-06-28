package com.cruise.thinking.in.spring.bean.lifecycle;

import com.cruise.thinking.in.spring.ioc.container.overview.container.IoCContainer;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 * <p>
 * 主要介绍通过Properties方式配置{@link BeanDefinition}，使用 {@link PropertiesBeanDefinitionReader}配合{@link DefaultListableBeanFactory} 实现
 * </p>
 *
 * <p>
 * 通过{@link XmlBeanDefinitionReader}配合{@link DefaultListableBeanFactory} 配置加载
 * {@link BeanDefinition}的方式请查看 {@link IoCContainer#beanFactoryAsIoCContainer()}
 * </p>
 *
 * @author Cruise
 * @version 1.0
 * @see XmlBeanDefinitionReader
 * @see PropertiesBeanDefinitionReader
 * @since 2020/6/23
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        // 解决中文乱码
        Resource resource = new ClassPathResource("META-INF/user.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int count = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("加载了" + count + "个BeanDefinition");
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
