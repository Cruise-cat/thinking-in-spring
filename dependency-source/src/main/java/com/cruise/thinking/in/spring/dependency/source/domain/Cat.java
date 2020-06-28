package com.cruise.thinking.in.spring.dependency.source.domain;

/**
 * 猫
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class Cat {

    private String color;

    public Cat() {
    }

    public Cat(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                '}';
    }
}
