package com.cruise.thinking.in.spring.bean.lifecycle.holder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * {@link Aware} holder 类
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/28
 */
public class AwareHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware, EnvironmentAware {

    private String beanName;
    private BeanFactory beanFactory;
    private ClassLoader classLoader;
    private ApplicationContext applicationContext;
    private Environment environment;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "AwareHolder{" +
                "beanName='" + beanName + '\'' +
                ", beanFactory=" + beanFactory +
                ", classLoader=" + classLoader +
                ", applicationContext=" + applicationContext +
                ", environment=" + environment +
                '}';
    }
}
