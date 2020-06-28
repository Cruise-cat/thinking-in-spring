package com.cruise.thinking.in.spring.dependency.injection;

import com.cruise.thinking.in.spring.dependency.injection.annotation.MyAutowired;
import com.cruise.thinking.in.spring.dependency.injection.annotation.Reference;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;

/**
 * 自定义 {@link Autowired}注解实现依赖注入
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class CustomAutowiredAnnotationDependencyInjectionDemo {

    private User user;
    private User user1;
    private User user2;

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @MyAutowired
    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Reference
    public void setUser2(User user2) {
        this.user2 = user2;
    }

    /**
     * 替换容器内的AutowiredAnnotationBeanPostProcessor
     * <p>
     *     如果不定义为 static方法时 由于 这个方法会替换Spring
     *     在 {@link AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry, Object)}
     *     中注册的 {@link AutowiredAnnotationBeanPostProcessor} 的 {@link BeanDefinition}
     *     而在给 {@link CustomAutowiredAnnotationDependencyInjectionDemo} 的属性进行依赖注入时，这个方法还没有执行
     *     导致{@link AutowiredAnnotationBeanPostProcessor}还没有被初始化 所以无法执行依赖注入 也就是说 {@link Autowired}
     *     等注解不会被扫描
     *
     *     如果将此方法定义为 static 方法，那么会在{@link CustomAutowiredAnnotationDependencyInjectionDemo}类加载
     *     的过程中就调用此方法对{@link AutowiredAnnotationBeanPostProcessor}被初始化，所以可以实现依赖注入
     * </p>
     *
     * <p>
     *     这种完全替换的方式 如果不手动加入 {@link Autowired}、{@link Value}、{@link Inject} 将不再支持
     * </p>
     *
     *
     * @return
     */
//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
//        Set annotations = new LinkedHashSet<>();
//        annotations.add(Autowired.class);
//        annotations.add(Value.class);
//        annotations.add(Inject.class);
//        annotations.add(Reference.class);
//        processor.setAutowiredAnnotationTypes(annotations);
//        return processor;
//    }


    /**
     * 这种方式不会清空{@link Autowired}、{@link Value}、{@link Inject}
     *
     * 而是在执行完 Spring 内置的 {@link AutowiredAnnotationBeanPostProcessor} 后再执行
     * 这个方法注入的自定义的 {@link AutowiredAnnotationBeanPostProcessor}，相当于此时容器内
     * 存在两个{@link AutowiredAnnotationBeanPostProcessor}的后置处理器
     *
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setAutowiredAnnotationType(Reference.class);
        return processor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(CustomAutowiredAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        CustomAutowiredAnnotationDependencyInjectionDemo bean = context.getBean(CustomAutowiredAnnotationDependencyInjectionDemo.class);

        System.out.println("bean.user:" + bean.user);
        System.out.println("bean.user1:" + bean.user1);
        System.out.println("bean.user2:" + bean.user2);

        context.close();

    }

}
