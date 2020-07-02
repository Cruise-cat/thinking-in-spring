package com.cruise.thinking.in.spring.configuration.metadata;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * 基于 Java 注解 的 IoC 容器元信息配置示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/30
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/bean-definitions.properties")
public class AnnotatedIoCContainerMetadataConfigurationDemo {

    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotatedIoCContainerMetadataConfigurationDemo.class);

        String[] definitionNames = context.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
        // user.name 不是配置的用户1，而是当前主机的用户名 Administrator，这是因为外部化属性配置的优先级的原因
        User user = context.getBean("configuredUser", User.class);
        System.out.println(user);

        context.close();
    }
}
