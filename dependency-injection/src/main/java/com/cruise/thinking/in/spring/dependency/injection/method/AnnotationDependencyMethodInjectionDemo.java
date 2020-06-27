package com.cruise.thinking.in.spring.dependency.injection.method;

import com.cruise.thinking.in.spring.dependency.injection.holder.UserHolder;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解手动方法注入示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;
    private UserHolder userHolder2;

    /**
     * @Autowired 使用在方法上，方法名是任意的,只有Spring 容器中存在参数类型的Bean
     *
     * @param userHolder
     */
    @Autowired
    public void init(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    /**
     * @Resource 使用在方法上，方法名是任意的
     *
     * @param userHolder2
     */
    @Resource
    public void init2(UserHolder userHolder2){
        this.userHolder2 = userHolder2;
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
        System.out.println(bean.userHolder == bean.userHolder2);


        context.close();

    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
