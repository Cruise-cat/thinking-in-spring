package com.cruise.thinking.in.spring.dependency.injection;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * 依赖注入处理过程
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 * @see DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String) 
 * @see DependencyDescriptor
 * @see AutowiredAnnotationBeanPostProcessor
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private User user;

    @Autowired
    private Map<String,User> userMap;

    @Autowired
    private Optional<User> optionalUser;

    @Inject
    private User injectUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        context.close();

    }
}
