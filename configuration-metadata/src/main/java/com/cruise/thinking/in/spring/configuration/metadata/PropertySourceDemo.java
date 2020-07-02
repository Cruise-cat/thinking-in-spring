package com.cruise.thinking.in.spring.configuration.metadata;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于 Properties 资源装载外部化配置
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/1
 */
@PropertySource("classpath:/META-INF/bean-definitions.properties")
public class PropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        context.register(PropertySourceDemo.class);

        // 扩展 Environment 中的 PropertySources
        // 添加 PropertySource 操作必须在 refresh 之前
        Map<String, Object> map = new HashMap<>();
        map.put("user.name","用户1");
        org.springframework.core.env.PropertySource mapPropertySource = new MapPropertySource("myPropertySource",map);
        context.getEnvironment().getPropertySources().addFirst(mapPropertySource);

        context.refresh();

        // user.name 不是配置的用户1，而是当前主机的用户名 Administrator，这是因为外部化属性配置的优先级的原因
        User user = context.getBean("user", User.class);
        System.out.println(user);

        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        System.out.println(propertySources);

        context.close();

    }
}
