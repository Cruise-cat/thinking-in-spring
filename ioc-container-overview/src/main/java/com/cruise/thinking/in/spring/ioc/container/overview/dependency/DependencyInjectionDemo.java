package com.cruise.thinking.in.spring.ioc.container.overview.dependency;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.Student;
import com.cruise.thinking.in.spring.ioc.container.overview.repository.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        // 根据名称注入
        injectionByName(beanFactory);
        // 根据类型注入
        injectionByType(beanFactory);
        // 注入容器内建Bean
        injectionByInnerBean(beanFactory);
        // 注入非Bean对象 报错
        injectionByNonBean(beanFactory);
        // 延迟注入
        injectionLazy(beanFactory);
        // BeanFactory or ApplicationContext who is IoC container
        whoIsIoC();
    }

    private static void injectionByName(BeanFactory beanFactory) {
        UserRepositoryByName repository = beanFactory.getBean(UserRepositoryByName.class);
        System.out.println("根据名称注入Bean：" + repository);
    }

    private static void injectionByType(BeanFactory beanFactory) {
        UserRepositoryByType repository = beanFactory.getBean(UserRepositoryByType.class);
        System.out.println("根据类型注入单个Bean：" + repository.getSuperUser());
        System.out.println("根据类型注入集合Bean：" + repository.getUsers());
    }

    /**
     * {@link Environment}并不是我们自定义的业务Bean 如果能够注入说明是容器内建的Bean
     *
     * @param beanFactory
     */
    private static void injectionByInnerBean(BeanFactory beanFactory) {
        RepositoryByInnerBean repository = beanFactory.getBean(RepositoryByInnerBean.class);
        System.out.println("注入容器内建Bean：" + repository.getEnvironment());
    }

    /**
     * 如果是一个Bean的话 应该可以通过 {@link BeanFactory#getBean(java.lang.Class)}方式获取Bean对象,
     * 否则就不是一个Bean，也就是一个非Bean,也可以说是容器内建的依赖
     * <p><i><b>可以依赖注入容器内建的依赖，但是无法依赖查找容器内建的依赖</b></i></p>
     *
     * <p>
     * 内建的Bean是普通的 Spring Bean，包括 BeanDefinitions 和 Singleton Objects，
     * 而内建依赖则是通过 {@link AutowireCapableBeanFactory#resolveDependency(DependencyDescriptor, String)}方法来注册，
     * 这并非是一个 Spring Bean，无法通过依赖查找获取
     *
     * @param beanFactory
     */
    private static void injectionByNonBean(BeanFactory beanFactory) {
        try {
            BeanFactory factoryBean = beanFactory.getBean(BeanFactory.class);
            // 报错了 无法获取 说明BeanFactory不是一个Bean对象而是一个容器内建依赖
            System.out.println("注入非Bean对象：" + factoryBean);
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过间接的方式注入，不直接注入依赖
     * <p>比如希望延迟依赖注入Student，可以通过依赖注入ObjectFactory<Student>来实现延迟注入Student</p>
     * 由于{@link Student}是一个原型Bean，而且是延迟依赖注入，所以在{@link ObjectFactory#getObject()}时才会注入{@link Student}
     *
     * @param beanFactory
     */
    private static void injectionLazy(BeanFactory beanFactory) {
        RepositoryLazy repositoryLazy = beanFactory.getBean(RepositoryLazy.class);
        ObjectFactory<Student> objectFactory = repositoryLazy.getObjectFactory();
        Student student = objectFactory.getObject();
        System.out.println("延迟注入：" + student);
    }

    /**
     * {@link ApplicationContext}继承了 {@link BeanFactory},
     * {@link AbstractApplicationContext}实现了{@link ApplicationContext}
     * 在{@link AbstractApplicationContext}中还组合了一个{@link BeanFactory}的实现{@link DefaultListableBeanFactory}
     * {@link ApplicationContext}中对Bean的管理还是通过的{@link BeanFactory}的实现,类似于代理模式
     * {@link ApplicationContext}含有{@link BeanFactory}的所有特性
     */
    public static void whoIsIoC() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        RepositoryForBeanFactory repository = applicationContext.getBean(RepositoryForBeanFactory.class);
        BeanFactory beanFactory = repository.getBeanFactory();
        System.out.println(beanFactory == applicationContext);//false
        System.out.println(beanFactory == applicationContext.getAutowireCapableBeanFactory());//true
    }
}
