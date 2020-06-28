package com.cruise.thinking.in.spring.bean.scope;

import com.cruise.thinking.in.spring.bean.scope.domain.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证 Bean Scope 与 Bean 的生命周期
 *
 * <p>
 * 无论是 singleton bean 还是 prototype bean 初始化方法都会执行
 * 但是prototype bean不会执行 销毁方法，所以如果使用prototype bean不当可能会造成OOM
 * </p>
 * <p>
 * 如果想实现对 prototype bean的销毁方法回调，可以在<br>
 * 1.{@link BeanPostProcessor#postProcessAfterInitialization(Object, String)}中对
 *  prototype bean进行销毁，但是不建议这么做，因为 Bean 初始化后一般是提供使用的 ，如果在这里被销毁 会产生问题<br>
 * 2.通过宿主在执行销毁方法回调时进行销毁prototype bean，建议
 * </p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/22
 */
public class BeanScopeAndLifecycleDemo {

    @Autowired
    private Person singletonPerson;

    @Autowired
    private Person prototypePerson;

    @Autowired
    private Map<String, Person> personMap;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Bean
    public Person singletonPerson() {
        Person person = new Person();
        person.setId(Instant.now().toEpochMilli());
        return person;
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person prototypePerson() {
        Person person = new Person();
        person.setId(Instant.now().toEpochMilli());
        return person;
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanScopeAndLifecycleDemo.class);

        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s 类型的Bean %s 被初始化 %n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        context.refresh();

        context.close();

        TimeUnit.SECONDS.sleep(2);

        System.gc();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("宿主bean开始销毁");
        this.prototypePerson.destroy();
        // 判断 Map中的元素是否是prototype bean
        for (Map.Entry<String, Person> entry : personMap.entrySet()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(entry.getKey());
            if (beanDefinition.isPrototype()) {
                entry.getValue().destroy();
            }
        }
        System.out.println("宿主bean销毁结束");
    }


}
