package com.cruise.thinking.in.spring.configuration.metadata.namespace;

import com.cruise.thinking.in.spring.configuration.metadata.parser.TeacherBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义 "teacher.xsd" 的 {@link NamespaceHandler} 实现
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/1
 */
public class TeachersNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        // 注册 teacher 元素的 BeanDefinition 注册实现
        registerBeanDefinitionParser("teacher", new TeacherBeanDefinitionParser());
    }
}
