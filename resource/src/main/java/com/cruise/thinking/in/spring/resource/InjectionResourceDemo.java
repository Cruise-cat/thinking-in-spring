package com.cruise.thinking.in.spring.resource;

import com.cruise.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Resource
 * @see Value
 * @see AutowiredAnnotationBeanPostProcessor
 * @since 2020/7/3
 */
public class InjectionResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultResource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @Value("${user.dir}")
    private String userDir;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(defaultResource));
        System.out.println("===============");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("===============");
        System.out.println(userDir);
    }


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InjectionResourceDemo.class);

        context.close();

    }
}
