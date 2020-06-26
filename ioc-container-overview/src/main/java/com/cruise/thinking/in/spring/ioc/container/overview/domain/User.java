package com.cruise.thinking.in.spring.ioc.container.overview.domain;

/**
 * 用户实体
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class User {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("Cruise");
        return user;
    }
}
