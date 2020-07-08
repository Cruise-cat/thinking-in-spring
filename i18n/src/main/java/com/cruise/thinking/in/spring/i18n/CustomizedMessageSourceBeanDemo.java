package com.cruise.thinking.in.spring.i18n;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Spring Boot 场景下自定义 {@link MessageSource} Bean
 *
 * @author Cruise
 * @version 1.0
 * @see MessageSource
 * @see ReloadableResourceBundleMessageSource
 * @see MessageSourceAutoConfiguration
 * @since 2020/7/5
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {

    @Bean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource(){
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
                .web(WebApplicationType.NONE).run(args);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {

            MessageSource messageSource = beanFactory.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);

            BeanDefinition definition = beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME);
            System.out.println(definition);
        }

        applicationContext.close();
    }
}
