package com.cruise.thinking.in.spring.data.binding.domain;

import com.cruise.thinking.in.spring.dependency.injection.enumeration.City;

/**
 * 员工
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/11
 */
public class Employee {

    private Long id;
    private String name;
    private City city;
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", company=" + company +
                '}';
    }
}
