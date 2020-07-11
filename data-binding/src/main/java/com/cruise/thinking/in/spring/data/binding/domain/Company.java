package com.cruise.thinking.in.spring.data.binding.domain;

/**
 * 公司
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/11
 */
public class Company {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
