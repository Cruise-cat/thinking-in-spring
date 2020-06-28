package com.cruise.thinking.in.spring.dependency.injection;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过{@link ObjectProvider}延迟依赖注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> users;

    @Autowired
    private ObjectFactory<User> users1;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        LazyAnnotationDependencyInjectionDemo bean = context.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println(bean.user);
        System.out.println("===========");

        bean.users.forEach(System.out::println);

        System.out.println("===========");

        System.out.println(bean.users1.getObject());
        context.close();

    }
}
