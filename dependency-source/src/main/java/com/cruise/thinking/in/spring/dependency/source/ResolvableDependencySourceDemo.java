package com.cruise.thinking.in.spring.dependency.source;

import com.cruise.thinking.in.spring.dependency.source.domain.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 手动注册 ResolvableDependency 进行依赖注入
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/21
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private Cat cat;

    @Autowired
    private String hi;

    @PostConstruct
    public void showMyCat() {
        System.out.println(hi);
        System.out.println(cat);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ResolvableDependencySourceDemo.class);

        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(Cat.class, new Cat("橘色"));
            beanFactory.registerResolvableDependency(String.class, "hi");
        });

        context.refresh();
        // Cat 不支持依赖查找
        Cat cat = context.getBean(Cat.class);
        System.out.println(cat);

        context.close();
    }
}
