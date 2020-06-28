package com.cruise.thinking.in.spring.dependency.source;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖源示例
 * <p>过程：</p>
 * <ol>
 *     <li>{@link DefaultListableBeanFactory#doResolveDependency}方法中的
 *     <pre class="code">
 *         Object value = getAutowireCandidateResolver().getSuggestedValue(descriptor);
 *     </pre>
 *     方法会解析外部化配置
 *     </li>
 * </ol>
 * @author Cruise
 * @version 1.0
 * @since 2020/6/21
 * @see AutowiredAnnotationBeanPostProcessor#postProcessProperties(PropertyValues, Object, String) 
 */
@Configuration
@PropertySource(value = "classpath:/META-INF/default.properties",encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${admin.id:-1}")
    private Long id;
    @Value("${admin.name}")
    private String name;
    @Value("${admin.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ExternalConfigurationDependencySourceDemo.class);

        context.refresh();

        ExternalConfigurationDependencySourceDemo bean = context.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("bean.id:" + bean.id);
        System.out.println("bean.name:" + bean.name);
        System.out.println("bean.resource:" + bean.resource);

        context.close();
    }
}
