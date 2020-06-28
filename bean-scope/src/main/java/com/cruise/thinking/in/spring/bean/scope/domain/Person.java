package com.cruise.thinking.in.spring.bean.scope.domain;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 人
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/22
 */
public class Person implements BeanNameAware {

    private Long id;

    private String name;

    private String beanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @PostConstruct
    public void init() {
        System.out.println(getBeanName() + " 初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(getBeanName() + " 销毁了");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(getBeanName() + " gc");

    }
}
