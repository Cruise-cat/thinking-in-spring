package com.cruise.thinking.in.spring.bean.lifecycle;

import com.cruise.thinking.in.spring.bean.lifecycle.holder.UserHolder;
import com.cruise.thinking.in.spring.bean.lifecycle.processor.MyInstantiationAwareBeanPostProcessor;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.SuperUser;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.SimpleInstantiationStrategy;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * Bean 的实例化示例
 *
 * <p>Bean 实例化过程</p>
 * <ol>
 *      <li>方法入口：{@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}</li>
 *     <li>代码：Object beanInstance = doCreateBean(beanName, mbdToUse, args);</li>
 *     <li>代码：instanceWrapper = createBeanInstance(beanName, mbd, args);</li>
 *     <ol>
 *         <li>obtainFromSupplier</li>
 *         <li>instantiateUsingFactoryMethod</li>
 *         <li>判断是否缓存了解析的构造方法或者工厂方法：mbd.resolvedConstructorOrFactoryMethod != null，
 *         一般第一次执行没有缓存，这里是重复创建相同bean的一个快照记录，避免每次创建都解析一遍</li>
 *         <li>determineConstructorsFromBeanPostProcessors 方法预判构造器，一般是空，因为没有自定义实现{@link SmartInstantiationAwareBeanPostProcessor}</li>
 *         <li>如果预判的构造器不为空，或者Autowiring 是 AUTOWIRE_CONSTRUCTOR或者BeanDefinition存在构造器参数或者args不是空
 *         执行autowireConstructor(beanName, mbd, ctors, args)</li>
 *         <li>mbd.getPreferredConstructors()判断是否存在偏好的构造器</li>
 *         <li>进入instantiateBean</li>
 *         <li>beanInstance = getInstantiationStrategy().instantiate(mbd, beanName, parent)</li>
 *         <li>调用{@link SimpleInstantiationStrategy#instantiate(RootBeanDefinition, String, BeanFactory)}通过Java反射的方式创建空对象</li>
 *     </ol>
 * </ol>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/24
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 也可以使用将MyInstantiationAwareBeanPostProcessor在xml中定义的方式注册到Spring容器
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml",
                "classpath:/META-INF/bean-constructor-dependency-injection.xml"};

        reader.loadBeanDefinitions(locations);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
        // 打断点查看构造器注入实例化对象的过程
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }

}
