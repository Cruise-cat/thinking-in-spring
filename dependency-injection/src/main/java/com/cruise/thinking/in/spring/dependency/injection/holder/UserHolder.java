package com.cruise.thinking.in.spring.dependency.injection.holder;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;

/**
 * {@link User} holder 类
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
