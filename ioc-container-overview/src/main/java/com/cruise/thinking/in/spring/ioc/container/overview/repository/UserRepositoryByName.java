package com.cruise.thinking.in.spring.ioc.container.overview.repository;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;

/**
 * 用户仓库，通过名称的方式注入
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class UserRepositoryByName {
    /**注入User类型中beanName是user的Bean*/
    private User user;
    /**注入User类型中beanName是superUser的Bean*/
    private User superUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSuperUser() {
        return superUser;
    }

    public void setSuperUser(User superUser) {
        this.superUser = superUser;
    }

    @Override
    public String toString() {
        return "UserRepositoryByName{" +
                "user=" + user +
                ", superUser=" + superUser +
                '}';
    }
}
