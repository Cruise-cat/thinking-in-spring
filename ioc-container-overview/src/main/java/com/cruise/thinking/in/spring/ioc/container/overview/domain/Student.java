package com.cruise.thinking.in.spring.ioc.container.overview.domain;

/**
 * 学生实体
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class Student {
    private Long id;
    private String name;

    public Student() {
        System.out.println("Student init");
    }

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

    public void initStudent(){
        System.out.println("initStudent");
    }

    public void destroyStudent(){
        System.out.println("destroyStudent");
    }
}
