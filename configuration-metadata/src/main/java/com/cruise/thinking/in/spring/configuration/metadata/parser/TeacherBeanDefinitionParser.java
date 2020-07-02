package com.cruise.thinking.in.spring.configuration.metadata.parser;

import com.cruise.thinking.in.spring.configuration.metadata.domain.Teacher;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * "teacher" 元素的 {@link BeanDefinitionParser}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/1
 */
public class TeacherBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Teacher.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id",element,builder);
        setPropertyValue("name",element,builder);
        setPropertyValue("city",element,builder);
    }

    private void setPropertyValue(String attribute, Element element, BeanDefinitionBuilder builder) {
        String value = element.getAttribute(attribute);
        if (StringUtils.hasText(value)) {
            builder.addPropertyValue(attribute, value);
        }
    }
}
