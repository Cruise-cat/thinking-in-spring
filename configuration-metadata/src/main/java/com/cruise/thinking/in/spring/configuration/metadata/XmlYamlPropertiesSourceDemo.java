package com.cruise.thinking.in.spring.configuration.metadata;

import com.cruise.thinking.in.spring.configuration.metadata.domain.Teacher;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于 xml 的 yaml 外部化配置示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/2
 * @see org.springframework.beans.factory.config.YamlProcessor
 * @see org.springframework.beans.factory.config.YamlMapFactoryBean
 */
public class XmlYamlPropertiesSourceDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitions = reader.loadBeanDefinitions("classpath:/META-INF/yaml-source-context.xml");
        System.out.println(beanDefinitions);

        Map<String,Object> bean = beanFactory.getBean("yamlMap",Map.class);
        System.out.println(bean);
    }

}
