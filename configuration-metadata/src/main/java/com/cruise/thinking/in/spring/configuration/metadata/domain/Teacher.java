package com.cruise.thinking.in.spring.configuration.metadata.domain;

import com.cruise.thinking.in.spring.dependency.injection.enumeration.City;

/**
 * 教师实体
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/1
 */
public class Teacher {

    private Long id;
    private String name;
    private City city;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
