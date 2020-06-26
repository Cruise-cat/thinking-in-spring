package com.cruise.thinking.in.spring.ioc.container.overview.dependency;

import com.cruise.thinking.in.spring.ioc.container.overview.annotation.Super;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        // 按照名称实时查找 Bean
        // lookupByNameRealTime(beanFactory);
        // 按照名称延时查找 Bean
        lookupByNameLazy(beanFactory);
        // 按照类型查找单个 Bean
        lookupByType(beanFactory);
        // 按照类型查找集合 Bean
        lookupCollectionByType(beanFactory);
        // 按照名称和类型查找 Bean
        lookupByNameAndType(beanFactory);
        // 按照注解查找 集合Bean
        lookupCollectionByAnnotation(beanFactory);
    }

    private static void lookupByNameRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("按照名称实时查找：" + user);
    }

    /**
     * {@link ObjectFactory}对象并不是直接返回了被依赖查找的Bean，而是一个Bean的查找代理。
     * 当得到{@link ObjectFactory}对象时，相当于Bean没有被创建，只有调用{@link ObjectFactory#getObject()}方法时，
     * 才会触发Bean实例化等生命周期。
     * <p>事实上，由于Spring上下文已经启动了，其管理的单例Bean都已经被初始化，所以如果查找一个单例Bean的话，该Bean很可能已经初始化了</p>
     *
     * @param beanFactory
     */
    private static void lookupByNameLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("按照名称延时查找：" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("按照类型查找单个对象：" + user);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("按照类型查找集合对象：" + users);
        }
    }

    public static void lookupByNameAndType(BeanFactory beanFactory) {
        User supperUser = beanFactory.getBean("superUser", User.class);
        System.out.println("按照名称和类型查找：" + supperUser);
    }

    public static void lookupCollectionByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("按照注解查找集合对象：" + users);
        }
    }
}
