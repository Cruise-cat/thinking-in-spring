package com.cruise.thinking.in.spring.ioc.container.overview.repository;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入{@link BeanFactory}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class RepositoryForBeanFactory {
    /**
     * 这里注入的{@link BeanFactory}是{@link DefaultListableBeanFactory}
     * <p>为什么注入的是{@link DefaultListableBeanFactory}?</p>
     * <p>因为{@link ApplicationContext}在启动时无论是通过{@link ClassPathXmlApplicationContext}
     * 还是{@link AnnotationConfigApplicationContext}默认创建的{@link BeanFactory}
     * 就是{@link DefaultListableBeanFactory}，也就是说{@link ApplicationContext}中默认的
     * {@link BeanFactory}就是{@link DefaultListableBeanFactory}。在创建了{@link BeanFactory}后。
     * 在{@link AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)}方法中将
     * 通过如下代码将其注入到了容器中
     * <pre class="code">
     *     beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
     * 	   beanFactory.registerResolvableDependency(ResourceLoader.class, this);
     * 	   beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
     * 	   beanFactory.registerResolvableDependency(ApplicationContext.class, this);
     * </pre>
     * </p>
     *
     */
    private BeanFactory beanFactory;

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
