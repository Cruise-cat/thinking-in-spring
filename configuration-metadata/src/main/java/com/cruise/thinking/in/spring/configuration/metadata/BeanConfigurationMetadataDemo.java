package com.cruise.thinking.in.spring.configuration.metadata;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.AttributeAccessor;
import org.springframework.util.ObjectUtils;

/**
 * Spring Bean 配置元信息示例
 *
 * @author Cruise
 * @version 1.0
 * @see AttributeAccessor
 * @see BeanMetadataElement
 * @since 2020/6/29
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        // BeanDefinition 的定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L)
                .addPropertyValue("name", "Cruise");
        // 获取 AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // attribute 仅仅是附加信息，不会影响 Bean 的实例化和属性赋值、初始化
        beanDefinition.setAttribute("name", "Tom");
        // source : 当前 BeanDefinition 的来源，可以基于不同来源的 BeanDefinition进行定制
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // 通过 BeanPostProcessor 结合 attribute 和 source 对 Bean 进行定制
                if (ObjectUtils.nullSafeEquals(beanName, "user") && bean.getClass().equals(User.class)) {
                    BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
                    if (definition.getSource().equals(BeanConfigurationMetadataDemo.class)) {
                        User user = (User) bean;
                        user.setName((String) definition.getAttribute("name"));
                        return user;
                    }
                }
                return null;
            }
        });

        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

    }
}
