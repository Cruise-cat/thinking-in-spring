package com.cruise.thinking.in.spring.configuration.metadata;

import com.cruise.thinking.in.spring.configuration.metadata.domain.Teacher;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 Extensible XML authoring 扩展 Spring XML 元素 示例
 * <p>步骤：</p>
 * <ul>
 *     <li>配置 teachers.xsd</li>
 *     <li>配置 teachers-context.xml</li>
 *     <li>配置 TeachersNamespaceHandler</li>
 *     <li>配置 TeacherBeanDefinitionParser</li>
 *     <li>配置 spring.handlers</li>
 *     <li>配置 spring.schemas</li>
 * </ul>
 * @author Cruise
 * @version 1.0
 * @since 2020/7/1
 */
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitions = reader.loadBeanDefinitions("classpath:/META-INF/teachers-context.xml");
        System.out.println(beanDefinitions);

        Teacher bean = beanFactory.getBean(Teacher.class);
        System.out.println(bean);
    }
}
