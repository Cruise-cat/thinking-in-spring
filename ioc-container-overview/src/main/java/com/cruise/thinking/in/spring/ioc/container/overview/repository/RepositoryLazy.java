package com.cruise.thinking.in.spring.ioc.container.overview.repository;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.Student;
import org.springframework.beans.factory.ObjectFactory;

/**
 * 延迟依赖注入
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class RepositoryLazy {

    private ObjectFactory<Student> objectFactory;

    public ObjectFactory<Student> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<Student> objectFactory) {
        this.objectFactory = objectFactory;
    }
}
