package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:META-INF/bean-definition-context.xml");
        // 通过id获取User
        User user = context.getBean("user", User.class);
        // 通过别名获取User
        User normalUser = context.getBean("commonUser", User.class);
        System.out.println(user == normalUser);// true
    }

}
