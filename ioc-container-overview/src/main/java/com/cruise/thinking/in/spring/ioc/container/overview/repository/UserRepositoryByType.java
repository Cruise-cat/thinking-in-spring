package com.cruise.thinking.in.spring.ioc.container.overview.repository;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.SuperUser;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;

import java.util.List;

/**
 * 用户仓库，通过类型的方式注入
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class UserRepositoryByType {
    /**注入所有User类型的Bean*/
    private List<User> users;
    /**应该注入的是SuperUser类型的Bean*/
    private SuperUser superUser;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public SuperUser getSuperUser() {
        return superUser;
    }

    public void setSuperUser(SuperUser superUser) {
        this.superUser = superUser;
    }

    @Override
    public String toString() {
        return "UserRepositoryByType{" +
                "users=" + users +
                ", superUser=" + superUser +
                '}';
    }
}
