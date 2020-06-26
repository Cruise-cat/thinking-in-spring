package com.cruise.thinking.in.spring.bean.definition.factory;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;

/**
 * {@link User}的 Bean 工厂
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
