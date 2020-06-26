package com.cruise.thinking.in.spring.ioc.container.overview.repository;

import org.springframework.core.env.Environment;

/**
 * 依赖注入容器内建Bean
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class RepositoryByInnerBean {

    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
