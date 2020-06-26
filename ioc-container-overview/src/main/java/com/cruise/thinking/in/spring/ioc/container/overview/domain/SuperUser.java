package com.cruise.thinking.in.spring.ioc.container.overview.domain;

import com.cruise.thinking.in.spring.ioc.container.overview.annotation.Super;

/**
 * 超级用户实体
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
@Super
public class SuperUser extends User{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "superUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
