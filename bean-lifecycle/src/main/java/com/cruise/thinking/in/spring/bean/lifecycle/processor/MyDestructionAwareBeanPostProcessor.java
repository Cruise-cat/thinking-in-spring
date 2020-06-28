package com.cruise.thinking.in.spring.bean.lifecycle.processor;

import com.cruise.thinking.in.spring.bean.lifecycle.holder.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * 自定义{@link DestructionAwareBeanPostProcessor}实现类
 *
 * <p>方法入口：{@link DisposableBeanAdapter#destroy()}</p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/28
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // v8 - v9
            userHolder.setDesc("user holder v9");
            System.out.println(userHolder.getDesc());
        }
    }
}
