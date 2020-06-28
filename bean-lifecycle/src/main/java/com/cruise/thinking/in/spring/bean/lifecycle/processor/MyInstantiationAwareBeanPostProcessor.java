package com.cruise.thinking.in.spring.bean.lifecycle.processor;

import com.cruise.thinking.in.spring.bean.lifecycle.holder.UserHolder;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.SuperUser;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.ObjectUtils;

/**
 * 自定义 {@link InstantiationAwareBeanPostProcessor}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/28
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * 实例化前阶段
     * <ol>
     *     <li>方法入口：{@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}</li>
     *     <li>代码：Object bean = resolveBeforeInstantiation(beanName, mbdToUse);</li>
     *     <li>这个方法的目的是返回一个代理</li>
     *     <li>如果这行代码返回的bean不是空将不会在执行剩下的实例化过程了</li>
     *     <li>一般这个方法都是返回空</li>
     * </ol>
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     * @see AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String, RootBeanDefinition)
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "superUser")
                && SuperUser.class.equals(beanClass)) {
            // 替代Spring IoC容器默认的实例化
            return new SuperUser();
        }
        // 仍然使用Spring IoC容器默认的实例化
        return null;
    }

    /**
     * 实例化后阶段
     * <li>方法入口：{@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)}</li>
     * <pre class="code">
     *     if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
     * 			for (BeanPostProcessor bp : getBeanPostProcessors()) {
     * 				if (bp instanceof InstantiationAwareBeanPostProcessor) {
     * 					InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
     * 					if (!ibp.postProcessAfterInstantiation(bw.getWrappedInstance(), beanName)) {
     * 						return;
     *                      }
     *               }
     *          }
     *    }
     * </pre>
     * 调用postProcessAfterInstantiation方法判断是否需要给Bean进行属性赋值，如果方法返回false则不进行
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
            // 手动属性赋值
            User user = (User) bean;
            user.setId(1000L);
            // 返回false Spring 容器将不会对Bean的属性进行赋值
            return false;
        }
        // 返回 true 仍然会进行赋值
        return true;
    }

    /**
     * 属性赋值前阶段
     *
     * <p>注意：如果{@link #postProcessBeforeInstantiation}不返回null不执行这个方法</p>
     * <p>注意：如果{@link #postProcessAfterInstantiation}返回false也不会执行此方法</p>
     * <p>方法入口：{@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)}</p>
     * <p>PropertyValues pvsToUse = ibp.postProcessProperties(pvs, bw.getWrappedInstance(), beanName);</p>
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            final MutablePropertyValues propertyValues;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            // 等价于 <property name="num" value="1"/>
            if (!propertyValues.contains("num")) {
                propertyValues.addPropertyValue("num", 1);
            }
            if (propertyValues.contains("desc")) {
                // PropertyValue的值是不能修改的 因为内部属性用final修饰了
                // PropertyValue desc = propertyValues.getPropertyValue("desc");
                // 替换已配置的属性
                propertyValues.removePropertyValue("desc");
                // v1 - v2
                propertyValues.add("desc", "user holder v2");
            }
            return propertyValues;
        }
        return null;
    }

    /**
     * 初始化前阶段
     * <p>方法入口：{@link AbstractAutowireCapableBeanFactory#initializeBean(Object, String)}</p>
     * <p>代码：wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);</p>
     *
     * @param bean
     * @param beanName
     * @return 如何返回null则使用原Bean，不返回null则替换Bean
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // v2 - v3
            userHolder.setDesc("user holder v3");
            return bean;
        }
        return null;
    }

    /**
     * 初始化后阶段
     *
     * <p>方法入口：{@link AbstractAutowireCapableBeanFactory#initializeBean(Object, String)}</p>
     * <p>代码：wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);</p>
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // v6 - v7
            userHolder.setDesc("user holder v7");
            return bean;
        }
        return null;
    }
}

