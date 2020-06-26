package com.cruise.thinking.in.spring.ioc.container.overview.container;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * IoC容器
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class IoCContainer {

    public static void main(String[] args) {
        // BeanFactory
        beanFactoryAsIoCContainer();
        // ApplicationContext
        applicationContextAsIoCContainer();
    }

    /**
     * 使用BeanFactory就无法使用事件机制、AOP等这些高级特性了
     *
     */
    public static void beanFactoryAsIoCContainer(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int count = reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        System.out.println("加载的Bean数量"+count);
        User user = beanFactory.getBean(User.class);
        System.out.println("通过BeanFactory获取Bean："+user);
    }

    private static void applicationContextAsIoCContainer() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IoCContainer.class);
        User user = context.getBean(User.class);
        System.out.println("通过ApplicationContext获取Bean："+user);
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(2L);
        user.setName("Tom");
        return user;
    }
}
