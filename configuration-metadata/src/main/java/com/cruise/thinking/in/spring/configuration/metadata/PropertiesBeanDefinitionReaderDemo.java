package com.cruise.thinking.in.spring.configuration.metadata;

import com.cruise.thinking.in.spring.dependency.injection.holder.CityHolder;
import com.cruise.thinking.in.spring.dependency.injection.holder.MultiCityHolder;
import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link PropertiesBeanDefinitionReader} 配置元信息示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/30
 */
public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        // Properties 默认使用 ISO-8859-1 字符集，实际文件字符集是 UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/META-INF/bean-definitions.properties");
        // 转换成带有字符编码的 EncodedResource
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        // 加载元信息
        int beanDefinitions = reader.loadBeanDefinitions(encodedResource);
        System.out.printf("加载了 %s 个 BeanDefinition%n", beanDefinitions);

        // 通过依赖查找获取 Bean
        User user = beanFactory.getBean("user",User.class);
        System.out.println(user);
        CityHolder cityHolder = beanFactory.getBean("cityHolder",CityHolder.class);
        System.out.println(cityHolder);
        MultiCityHolder multiCityHolder = beanFactory.getBean("multiCityHolder",MultiCityHolder.class);
        System.out.println(multiCityHolder);
    }
}
