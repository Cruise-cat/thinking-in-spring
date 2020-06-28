package com.cruise.thinking.in.spring.bean.lifecycle;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.SuperUser;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * Bean 的实例化示例
 *
 * @author Cruise
 * @version 1.0
 * @see AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String, org.springframework.beans.factory.support.RootBeanDefinition)
 * @since 2020/6/24
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "superUser")
                    && SuperUser.class.equals(beanClass)) {
                // 替代Spring IoC容器默认的实例化
                return new SuperUser();
            }
            // 仍然使用Spring IoC容器默认的实例化
            return null;
        }
    }
}
