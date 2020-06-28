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
 * 通过 {@link Qualifier} 限定依赖注入示例
 * <p>可以通过 {@link Qualifier} 对 Bean 进行分组</p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class QualifierDependencyInjectionDemo {

    /**
     * 注入 User 类型的 Bean ，如果存在多个则注入 Primary 的 Bean
     *
     */
    @Autowired
    private User user;

    /**
     * 注入 User 类型的 且 beanName是 user的
     *
     */
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
        User user = new User();
        user.setId(1L);
        return user;
    }

    @Bean
    @Primary
    public SuperUser superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setId(2L);
        return superUser;
    }

    @Bean
    @Qualifier // 进行分组
    public User user1() {
        User user = new User();
        user.setId(3L);
        return user;
    }

    @Bean
    @Qualifier // 进行分组
    public User user2() {
        User user = new User();
        user.setId(4L);
        return user;
    }

    @Bean
    @UserGroup
    public User user3() {
        User user = new User();
        user.setId(5L);
        return user;
    }

    @Bean
    @UserGroup
    public User user4() {
        User user = new User();
        user.setId(6L);
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
        // 输出 全部 User,包含使用 @Qualifier 和 @UserGroup 注册的 Bean
        System.out.println("bean.allUsers" + bean.allUsers);
        // 输出 通过 @Qualifier 分组的 User，仅包含使用 @Qualifier 和 @UserGroup 注册的 Bean
        System.out.println("bean.qualifierUsers" + bean.qualifierUsers);
        // 输出 通过 @UserGroup 扩展的 User，仅包含使用 @UserGroup 注册的 Bean
        System.out.println("bean.groupUsers" + bean.groupUsers);
        context.close();
    }
}
