package com.cruise.thinking.in.spring.dependency.injection;

import com.cruise.thinking.in.spring.dependency.injection.annotation.UserGroup;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.SuperUser;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * {@link Qualifier} 限定依赖注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class QualifierDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private List<User> allUsers;

    @Autowired
    @Qualifier
    private List<User> qualifierUsers;

    @Autowired
    @UserGroup
    private List<User> groupUsers;

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    @Primary
    public SuperUser superUser() {
        return new SuperUser();
    }

    @Bean
    @Qualifier // 进行分组
    public User user1() {
        User user = new User();
        user.setId(7L);
        return user;
    }

    @Bean
    @Qualifier // 进行分组
    public User user2() {
        User user = new User();
        user.setId(8L);
        return user;
    }

    @Bean
    @UserGroup
    public User user3() {
        User user = new User();
        user.setId(9L);
        return user;
    }

    @Bean
    @UserGroup
    public User user4() {
        User user = new User();
        user.setId(10L);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(QualifierDependencyInjectionDemo.class);

        QualifierDependencyInjectionDemo bean = context.getBean(QualifierDependencyInjectionDemo.class);

        // 输出 Primary 的 User
        System.out.println("bean.user" + bean.user);
        // 输出 限定了名称的 User
        System.out.println("bean.namedUser" + bean.namedUser);
        // 输出 全部 User
        System.out.println("bean.allUsers" + bean.allUsers);
        // 输出 通过 @Qualifier 分组的 User
        System.out.println("bean.qualifierUsers" + bean.qualifierUsers);
        // 输出 通过 @UserGroup 扩展的 User
        System.out.println("bean.groupUsers" + bean.groupUsers);
        context.close();
    }
}
