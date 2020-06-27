package com.cruise.thinking.in.spring.bean.definition.factory;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认的{@link User}的 Bean 工厂
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct");
    }

    public void myInit(){
        System.out.println("myInit");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy");
    }

    public void myDestroy(){
        System.out.println("myDestroy");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
