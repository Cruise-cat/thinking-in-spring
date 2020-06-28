package com.cruise.thinking.in.spring.dependency.injection.method;

import com.cruise.thinking.in.spring.dependency.injection.holder.UserHolder;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * 基于注解手动方法注入示例
 * <p>Setter 注入和方法注入的区别？</p>
 * <p>Setter 注入是通过 Java Beans 来实现的，而方法注入则是直接通过 Java 反射来做的。当然底层都是 Java 反射</p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;
    private UserHolder userHolder2;
    private UserHolder userHolder3;

    /**
     * {@link Autowired} 使用在方法上，方法名是任意的,只需要 Spring 容器中存在 参数类型的 Bean
     *
     * @param userHolder
     */
    @Autowired
    public void init(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    /**
     * {@link Resource} 使用在方法上，方法名是任意的
     *
     * @param userHolder2
     */
    @Resource
    public void init2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    /**
     * {@link Inject} 使用在方法上，方法名是任意的
     *
     * @param userHolder3
     */
    @Inject
    public void init3(UserHolder userHolder3) {
        this.userHolder3 = userHolder3;
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotationDependencyMethodInjectionDemo.class);

        // 使用注解的方式启动Spring上下文也可以使用 Xml的特性
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        AnnotationDependencyMethodInjectionDemo bean = context.getBean(AnnotationDependencyMethodInjectionDemo.class);

        System.out.println(bean.userHolder);
        System.out.println(bean.userHolder2);
        System.out.println(bean.userHolder3);
        System.out.println(bean.userHolder == bean.userHolder2);
        System.out.println(bean.userHolder == bean.userHolder3);

        context.close();
    }

}
