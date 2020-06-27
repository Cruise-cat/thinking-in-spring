package com.cruise.thinking.in.spring.dependency.injection.field;

import com.cruise.thinking.in.spring.dependency.injection.holder.UserHolder;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解手动实例字段注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private UserHolder userHolder;

    /**
     * @Autowired 会自动忽略static属性
     * @Autowired
     * private static UserHolder userHolder;
     */

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotationDependencyFieldInjectionDemo.class);

        // 使用注解的方式启动Spring上下文也可以使用 Xml的特性
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        AnnotationDependencyFieldInjectionDemo bean = context.getBean(AnnotationDependencyFieldInjectionDemo.class);

        System.out.println(bean.userHolder);
        System.out.println(bean.userHolder2);
        System.out.println(bean.userHolder == bean.userHolder2);


        context.close();

    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
