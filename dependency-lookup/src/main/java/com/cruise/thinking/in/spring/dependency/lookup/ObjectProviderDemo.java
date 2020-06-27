package com.cruise.thinking.in.spring.dependency.lookup;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import java.util.Iterator;

/**
 * 通过 {@link ObjectProvider} 进行延迟依赖查找
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 * @see ObjectProvider
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ObjectProviderDemo.class);
        lookupByObjectProvider(context);

        lookupIfAvailable(context);

        lookupStreamOps(context);

        context.close();

    }

    public static void lookupByObjectProvider(AnnotationConfigApplicationContext context){
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        // ObjectProvider#getObject() 只能在只有一个String类型的单例时可以获取？添加@Primary注解即可
        System.out.println(beanProvider.getObject());
    }

    /**
     * 使用Lambda表达式
     *
     * @param context
     */
    public static void lookupIfAvailable(AnnotationConfigApplicationContext context){
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        // 提供一个兜底方案
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println(user);
    }

    /**
     * 使用 Stream API
     *
     * @param context
     */
    public static void lookupStreamOps(AnnotationConfigApplicationContext context){
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        // 1 ObjectProvider 实现了 Iterator
        Iterator<String> iterator = beanProvider.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());
        // 2
        beanProvider.stream().forEach(System.out::println);
    }


    @Bean
    @Primary
    public String hello(){
        return "hello";
    }

    @Bean String hi(){
        return "hi";
    }
}
