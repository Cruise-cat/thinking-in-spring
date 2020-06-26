package com.cruise.thinking.in.spring.bean.definition.factory;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User}的 {@link FactoryBean}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    /**
     * 是否是单例 Bean
     *
     * @return true 会缓存第一次获取的 Bean,false 每次调 {@link #getObject()} 方法都会返回一个新的 Bean
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
