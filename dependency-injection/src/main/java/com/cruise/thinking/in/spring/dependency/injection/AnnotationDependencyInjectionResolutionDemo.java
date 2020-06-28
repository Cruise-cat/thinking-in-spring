package com.cruise.thinking.in.spring.dependency.injection;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Provider;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 依赖注入处理过程
 * <p>在{@link DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)}
 * 方法打断点观察不同类型的依赖注入的过程</p>
 * 过程解析：
 * <ol>
 *     <li>判断参数类型是不是 Optional</li>
 *     <li>判断参数类型是不是 ObjectFactory 或者 ObjectProvider</li>
 *     <li>判断参数类型是不是{@link Provider}</li>
 *     <li>{@link AutowireCandidateResolver#getLazyResolutionProxyIfNecessary(DependencyDescriptor, String)}判断是否需要延迟依赖注入，需要则返回一个CGLIB代理对象</li>
 *     <li>{@link DefaultListableBeanFactory#resolveMultipleBeans(DependencyDescriptor, String, Set, TypeConverter)}判断参数的包装类型是不是List、Map、Array等类型</li>
 *     <li>如果是单一包装类型：</li>
 *     <li>{@link DefaultListableBeanFactory#findAutowireCandidates(String, Class, DependencyDescriptor)}取出所有候选Bean</li>
 *     <li>取出候选Bean时会对ResolvableDependency进行判断处理，比如判断是不是注入的BeanFactory等类型</li>
 *     <li>如果required = true 但是没有候选的 Bean 抛出 {@link NoSuchBeanDefinitionException}异常</li>
 *     <li>如果只有一个候选Bean 则返回并注入</li>
 *     <li>如果有多个候选Bean，则通过Primary等判断选择其中一个</li>
 * </ol>
 *
 * <p>{@link Autowired} 注入原理 </p>
 * <ol>
 *     <li>{@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)}是属性填充的入口，
 *     这个方法里对{@link InstantiationAwareBeanPostProcessor}进行回调</li>
 *     <li>代码：PropertyValues pvsToUse = ibp.postProcessProperties(pvs, bw.getWrappedInstance(), beanName);</li>
 *     <li>调{@link AutowiredAnnotationBeanPostProcessor#postProcessProperties(PropertyValues, Object, String)}方法</li>
 *     <li>通过{@link AutowiredAnnotationBeanPostProcessor#findAutowiringMetadata(String, Class, PropertyValues)}方法筛选出
 *     标注了{@link Autowired}、{@link Value}、{@link Inject}注解的属性或方法</li>
 *     <li>上一步其实会使用缓存，因为 Spring 在调用 {@link AutowiredAnnotationBeanPostProcessor#postProcessMergedBeanDefinition(RootBeanDefinition, Class, String)}
 *     时也调了findAutowiringMetadata提前做了缓存</li>
 *     <li>在{@link InjectionMetadata#inject(Object, String, PropertyValues)}方法中循环进行依赖注入</li>
 *     <li>在{@link AutowiredAnnotationBeanPostProcessor.AutowiredFieldElement#inject}方法中调用了上面的
 *     {@link DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)}方法获取
 *     需要被注入的Bean</li>
 *     <li>先通过参数类型获取，如果存在多个类型的Bean，且存在primary Bean则注入Primary Bean</li>
 *     <li>如果不存在Primary Bean 则通过名称判断候选的Bean</li>
 *     <li>获取到Bean后通过Java反射的方式设置宿主类的属性#{@link Field#set(Object, Object)}</li>
 *     <b>{@link AutowiredAnnotationBeanPostProcessor#postProcessProperties(PropertyValues, Object, String)}方法
 *     会在Setter方法之前进行调用</b>
 * </ol>
 *
 * <p>Java 通用注解的依赖注入处理过程，是通过{@link CommonAnnotationBeanPostProcessor}中实现的，原理和{@link Autowired}差不多</p>
 * <p>{@link CommonAnnotationBeanPostProcessor}中还有{@link PostConstruct}和{@link PreDestroy}的处理</p>
 * @author Cruise
 * @version 1.0
 * @see DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String, Set, TypeConverter)
 * @see DependencyDescriptor
 * @see AutowiredAnnotationBeanPostProcessor
 * @since 2020/6/27
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    /**
     * {@link DependencyDescriptor}：
     * <ul>
     *     <li>required = true</li>
     *     <li>eager = true</li>
     *     <li>fieldName = user</li>
     *     <li>containingClass = User.class</li>
     *     <li>declaringClass = AnnotationDependencyInjectionResolutionDemo.class</li>
     *     <li>primary = true</li>
     * </ul>
     */
    @Autowired
    private User user;

    @Autowired
    private Map<String, User> userMap;

    @Autowired
    private Optional<User> optionalUser;

    @Inject
    private User injectUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);

        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        context.close();

    }
}
