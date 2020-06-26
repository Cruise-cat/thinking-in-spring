package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import com.cruise.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * 特殊方式实例化 Bean
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        serviceLoaderDemo();

        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-instantiation-context.xml");

        // 通过ServiceLoaderFactoryBean实例化Bean
        ServiceLoader<UserFactory> serviceLoader = context.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);

        // 通过AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)实例化Bean
        AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        DefaultUserFactory defaultUserFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(defaultUserFactory);

    }

    /**
     * {@link ServiceLoader} 是Java提供的API，通过扫描 META-INF/services/ 路径下文件取到类的实例
     *
     * @see ServiceLoader
     */
    public static void serviceLoaderDemo() {
        ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
