package com.cruise.thinking.in.spring.bean.lifecycle.holder;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link User} holder 类
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/28
 */
public class UserHolder implements InitializingBean, SmartInitializingSingleton, DisposableBean {

    private User user;

    private Integer num;

    private String desc;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", num=" + num +
                ", desc='" + desc + '\'' +
                '}';
    }

    @PostConstruct
    public void postConstruct(){
        // v3 - v4
        System.out.println(this.desc);
        this.desc = "user holder v4";
        System.out.println(this.desc);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // v4 - v5
        this.desc = "user holder v5";
        System.out.println(this.desc);
    }

    public void init (){
        // v5 - v6
        this.desc = "user holder v6";
        System.out.println(this.desc);
    }

    @Override
    public void afterSingletonsInstantiated() {
        // v7 - v8
        this.desc = "user holder v8";
        System.out.println(this.desc);
    }

    @PreDestroy
    public void preDestroy(){
        // v9 - v10
        this.desc = "user holder v10";
        System.out.println(this.desc);

    }


    @Override
    public void destroy() throws Exception {
        // v10 - v11
        this.desc = "user holder v11";
        System.out.println(this.desc);
    }

    public void myDestroy(){
        // v11 - v12
        this.desc = "user holder v12";
        System.out.println(this.desc);
    }

    @Override
    protected void finalize() throws Throwable {
        // v12 - v13
        this.desc = "user holder v13";
        System.out.println(this.desc);
    }
}
