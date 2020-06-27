package com.cruise.thinking.in.spring.dependency.lookup;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全依赖查找示例
 *
 * @author Cruise
 * @version 1.0
 * @see ObjectFactory
 * @see ObjectProvider
 * @since 2020/6/27
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TypeSafetyDependencyLookupDemo.class);
        //  单一类型查找
        lookupByBeanFactoryGetBean(context);
        lookupByObjectFactoryGetObject(context);
        lookupByObjectProviderIfAvailable(context);

        // 集合类型查找
        lookupByListableBeanFactory(context);
        lookupByObjectProviderStreamOps(context);

        context.close();
    }

    /**
     * 不安全
     *
     * @param context Spring 上下文
     * @throws NoSuchBeanDefinitionException
     */
    private static void lookupByBeanFactoryGetBean(AnnotationConfigApplicationContext context) {
        displayException("lookupByBeanFactoryGetBean", () -> context.getBean(User.class));
    }

    /**
     * 不安全
     * NoSuchBeanDefinitionException
     *
     * @param context Spring 上下文
     * @throws NoSuchBeanDefinitionException
     */
    private static void lookupByObjectFactoryGetObject(AnnotationConfigApplicationContext context) {
        ObjectFactory<User> objectFactory = context.getBeanProvider(User.class);
        displayException("lookupByObjectFactoryGetObject", () -> objectFactory.getObject());
    }

    /**
     * 类型安全
     * <p>{@link ObjectProvider}是{@link ObjectFactory}的子类，而且还提供了类型安全的依赖查找</p>
     *
     * @param context Spring 上下文
     */
    private static void lookupByObjectProviderIfAvailable(AnnotationConfigApplicationContext context) {

        ObjectProvider<User> objectProvider = context.getBeanProvider(User.class);
        displayException("lookupByObjectProviderIfAvailable", () -> objectProvider.getIfAvailable());
    }

    /**
     * 类型安全
     *
     * @param beanFactory bean工厂
     */
    private static void lookupByListableBeanFactory(ListableBeanFactory beanFactory) {
        displayException("lookupByListableBeanFactory", () -> beanFactory.getBeansOfType(User.class));

    }

    /**
     * 类型安全
     *
     * @param context Spring 上下文
     */
    private static void lookupByObjectProviderStreamOps(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> objectProvider = context.getBeanProvider(User.class);
        displayException("lookupByListableBeanFactory", () -> objectProvider.stream().forEach(System.out::println));
    }


    public static void displayException(String method, Runnable runnable) {
        System.err.println("==========================");
        System.err.println("method :" + method);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }
}
