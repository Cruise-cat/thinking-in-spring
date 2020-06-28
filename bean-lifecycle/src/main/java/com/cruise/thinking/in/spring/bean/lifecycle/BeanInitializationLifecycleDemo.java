package com.cruise.thinking.in.spring.bean.lifecycle;

import com.cruise.thinking.in.spring.bean.lifecycle.holder.UserHolder;
import com.cruise.thinking.in.spring.bean.lifecycle.processor.MyDestructionAwareBeanPostProcessor;
import com.cruise.thinking.in.spring.bean.lifecycle.processor.MyInstantiationAwareBeanPostProcessor;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.AbstractApplicationContext;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Bean 的初始化声明周期示例
 * <p>@PostConstruct执行：</p>
 * <p>{@link AbstractAutowireCapableBeanFactory#initializeBean(Object, String)}</p>
 * <p>代码：wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);</p>
 * <p>{@link InitializingBean}和自定义初始化方法：</p>
 * <p>代码：invokeInitMethods(beanName, wrappedBean, mbd);</p>
 * <p>先执行{@link InitializingBean#afterPropertiesSet()}</p>
 * <p>然后执行自定义初始化方法</p>
 *
 * <p>销毁：</p>
 * <p>{@link AbstractApplicationContext#destroyBeans()}</p>
 * @author Cruise
 * @version 1.0
 * @since 2020/6/28
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // addBeanPostProcessor的注册顺序很重要，先注册哪个就先执行哪个，如果先注册CommonAnnotationBeanPostProcessor
        // 那么就会先执行CommonAnnotationBeanPostProcessor的@postConstruct方法
        // 也可以使用将MyInstantiationAwareBeanPostProcessor在xml中定义的方式注册到Spring容器
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加对@PostConstruct的支持
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        // 添加 自定义销毁实现
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml",
                "classpath:/META-INF/bean-constructor-dependency-injection.xml"};

        reader.loadBeanDefinitions(locations);

        // 触发 SmartInitializingSingleton的回调，这个时候所有的单例Bean都已初始化结束
        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
        // 打断点查看构造器注入实例化对象的过程
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);

        // 显示触发 Bean销毁 的回调
        // 销毁Bean并不代表被 GC了
        beanFactory.destroyBean("userHolder", userHolder);

        System.out.println(userHolder);

        userHolder = null;
        beanFactory.destroySingletons();
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.gc();
        TimeUnit.SECONDS.sleep(1);
    }
}
