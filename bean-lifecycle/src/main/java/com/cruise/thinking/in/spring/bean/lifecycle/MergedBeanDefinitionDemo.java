package com.cruise.thinking.in.spring.bean.lifecycle;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanDefinition合并示例
 * <br>
 * 合并过程分析：
 * <p>1.此时的mergedBeanDefinitions是空集合</p>
 * <p>2.通过依赖查找先获取user,此时user是一个{@link GenericBeanDefinition},但是user不存在parentName
 * 所以最终user会变成{@link RootBeanDefinition}并加入到当前BeanFactory的 mergedBeanDefinitions</p>
 * <p>3.开始依赖查找superUser,此时superUser也是一个{@link GenericBeanDefinition}，但是superUser的
 * BeanDefinition存在parentName，所以会找到缓存中的user的RootBeanDefinition,这里其实是一个递归的过程，会一直
 * 找到顶级父类的RootBeanDefinition，找到就将当前BeanDefinition和其父BeanDefinition合并，最终
 * 然后将superUser的属性覆盖通过user找到的RootBeanDefinition，这样superUser
 * 就是一个合并后的RootBeanDefinition</p>
 * @author Cruise
 * @version 1.0
 * @since 2020/6/24
 * @see AbstractBeanFactory#getMergedBeanDefinition(String)  打断点观察合并过程
 */
public class MergedBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

    }
}
