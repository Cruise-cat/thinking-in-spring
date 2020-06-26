package com.cruise.thinking.in.spring.bean.definition;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.SuperUser;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition}构建示例
 * <p>Java Bean 与 Spring Bean 的区别？</p>
 * 广义地来看，Java Bean 是一个表现形式，而 Spring Bean 是狭义地托管在 Spring 容器中的 Java Bean
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 * @see BeanDefinition
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        createByBeanDefinitionBuilder();
        createByAbstractBeanDefinition();
    }

    /**
     * 通过{@link BeanDefinitionBuilder}构建
     *
     * @see BeanDefinitionBuilder
     */
    public static void createByBeanDefinitionBuilder(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性
        beanDefinitionBuilder
                .addPropertyValue("id",1L)
                .addPropertyValue("name","Cruise");
        // 获取BeanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition并非终态 仍然可以修改
        beanDefinition.setLazyInit(false);
    }

    /**
     * 通过{@link AbstractBeanDefinition}及其派生类构建
     *
     * @see AbstractBeanDefinition
     */
    public static void createByAbstractBeanDefinition(){
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置Bean 类型
        genericBeanDefinition.setBeanClass(SuperUser.class);
        // 设置属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues
                .add("id",1L)
                .add("name","Cruise")
                .add("address","Beijing");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
