package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 垃圾回收 Spring Bean 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanDestroyDemo.class);
        System.out.println(context.getBean(UserFactory.class));
        context.close();
        System.out.println("Spring上下文关闭了");

        Thread.sleep(2000);
        // 是可以被GC的
        System.gc();
    }
}
