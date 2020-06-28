package com.cruise.thinking.in.spring.bean.scope.configuration;

import com.cruise.thinking.in.spring.bean.scope.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 激活 Web MVC 配置
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/22
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public Person requestPerson(){
        Person person = new Person();
        person.setId(1L);
        person.setName("用户1");
        return person;
    }

    @Bean
    @SessionScope
    public Person sessionPerson(){
        Person person = new Person();
        person.setId(2L);
        person.setName("用户2");
        return person;
    }

    @Bean
    @ApplicationScope
    public Person applicationPerson(){
        Person person = new Person();
        person.setId(3L);
        person.setName("用户3");
        return person;
    }
}
