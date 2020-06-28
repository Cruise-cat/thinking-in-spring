package com.cruise.thinking.in.spring.bean.scope;

import com.cruise.thinking.in.spring.bean.scope.domain.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.Instant;

/**
 * 注册自定义Scope并测试
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/22
 */
public class ThreadLocalScopeRegistryDemo {

    @Bean
    @Scope(scopeName = ThreadLocalScope.SCOPE_NAME)
    public Person person() {
        Person person = new Person();
        person.setId(Instant.now().toEpochMilli());
        return person;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        context.register(ThreadLocalScopeRegistryDemo.class);
        context.refresh();

        for (int i = 0; i < 3; i++) {
            // 验证相同线程依赖查找的Person是相同的
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    Person person = context.getBean(Person.class);
                    System.out.println(Thread.currentThread().getId() + "bean.person" + person.getId());
                }
            });
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        context.close();
    }
}
