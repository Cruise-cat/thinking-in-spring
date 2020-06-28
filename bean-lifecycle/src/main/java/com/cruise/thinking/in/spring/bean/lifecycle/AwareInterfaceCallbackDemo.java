package com.cruise.thinking.in.spring.bean.lifecycle;

import com.cruise.thinking.in.spring.bean.lifecycle.holder.AwareHolder;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * {@link Aware} 接口回调示例
 * <p>回调的顺序</p>
 * <li>BeanNameAware</li>
 * <li>BeanClassLoaderAware</li>
 * <li>BeanFactoryAware</li>
 * <li>EnvironmentAware</li>
 * <li>EmbeddedValueResolverAware</li>
 * <li>ResourceLoaderAware</li>
 * <li>ApplicationEventPublisherAware</li>
 * <li>MessageSourceAware</li>
 * <li>ApplicationContextAware</li>
 * <p>前三个通过{@link AbstractAutowireCapableBeanFactory#invokeAwareMethods(String, Object)}回调</p>
 * <p>后面的必须在ApplicationContext环境中才会回调</p>
 * <p>在此方法注册到 Spring 容器中 {@link AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)}</p>
 * <pre class="code">
 *  beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
 * 	beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
 * 	beanFactory.ignoreDependencyInterface(EmbeddedValueResolverAware.class);
 * 	beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
 * 	beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
 * 	beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
 * 	beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);
 * </pre>
 * <p>回调setXXXAware方法入口：{@link AbstractAutowireCapableBeanFactory#initializeBean(Object, String)}</p>
 * <p>代码：wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);</p>
 * <p>{@link ApplicationContextAwareProcessor#postProcessBeforeInitialization(Object, String)}实现的</p>
 * @author Cruise
 * @version 1.0
 * @since 2020/6/28
 */
public class AwareInterfaceCallbackDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareHolder.class);
        context.refresh();

        AwareHolder holder = context.getBean(AwareHolder.class);
        System.out.println(holder);

        context.close();
    }
}
