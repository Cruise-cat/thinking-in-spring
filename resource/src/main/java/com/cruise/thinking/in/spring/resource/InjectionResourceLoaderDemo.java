package com.cruise.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link ResourceLoader} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/3
 */
public class InjectionResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader defaultResourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.defaultResourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        System.out.println("defaultResourceLoader == resourceLoader :" + (defaultResourceLoader == resourceLoader));
        System.out.println("defaultResourceLoader == applicationContext :" + (defaultResourceLoader == applicationContext));
    }


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectionResourceLoaderDemo.class);

        context.close();

    }
}
