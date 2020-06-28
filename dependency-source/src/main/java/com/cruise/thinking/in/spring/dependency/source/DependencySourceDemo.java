package com.cruise.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 * <p>
 *     依赖来源相比依赖注入多了一个 非 Spring 容器管理对象，Spring 通过
 *     {@link AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)}
 *     注册了如下四个Bean：
 * <pre class="code">
 * beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
 * beanFactory.registerResolvableDependency(ResourceLoader.class, this);
 * beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
 * beanFactory.registerResolvableDependency(ApplicationContext.class, this);
 * 这四个Bean不支持依赖查找但是支持依赖注入
 * </pre>
 * </p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/21
 */
public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * ResolvableDependency支持依赖注入
     */
    @PostConstruct
    public void checkDependencyInjection(){
        // false
        System.out.println("beanFactory == applicationContext ? "+(beanFactory == applicationContext));
        // true
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory() ? "+(beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        // true
        System.out.println("resourceLoader == applicationContext ? "+(resourceLoader == applicationContext));
        // true
        System.out.println("applicationEventPublisher == applicationContext ? "+(applicationEventPublisher == applicationContext));
    }

    /**
     * ResolvableDependency不支持依赖查找
     *
     */
    @PostConstruct
    public void checkDependencyLookup(){
        lookup(BeanFactory.class);
        lookup(ApplicationContext.class);
        lookup(ResourceLoader.class);
        lookup(ApplicationEventPublisher.class);
    }

    private <T> T lookup(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println(beanType + "类型不存在");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DependencySourceDemo.class);
        context.refresh();
        context.close();
    }
}
