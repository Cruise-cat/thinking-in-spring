package com.cruise.thinking.in.spring.validation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Spring Bean Validation 整合示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/8
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

        Validator bean = context.getBean(Validator.class);
        System.out.println(bean);
        System.out.println(bean instanceof LocalValidatorFactoryBean);

        UserConfiguration userConfiguration = context.getBean(UserConfiguration.class);
        userConfiguration.process(new User());

        context.close();
    }

    @Configuration(proxyBeanMethods = false)
    @Validated
    static class UserConfiguration {

        void process(@Valid User user) {
            System.out.println(user);
        }
    }

    static class User {
        private Integer id;
        @NotNull(message = "name 必传")
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
