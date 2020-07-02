package com.cruise.thinking.in.spring.configuration.metadata;

import com.cruise.thinking.in.spring.configuration.metadata.domain.Teacher;
import com.cruise.thinking.in.spring.dependency.injection.enumeration.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 基于 Java 注解的 yaml 外部化配置示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/2
 */
@PropertySource(name = "yamlPropertiesSource", value = "classpath:/META-INF/teacher.yaml"
        , factory = YamlPropertiesSourceFactory.class)
public class AnnotatedYamlPropertiesSourceDemo {


    @Bean
    public Teacher teacher(@Value("${teacher.id}") Long id, @Value("${teacher.name}") String name,
                           @Value("${teacher.city}") City city) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setCity(city);
        return teacher;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotatedYamlPropertiesSourceDemo.class);

        Teacher teacher = context.getBean("teacher", Teacher.class);
        System.out.println(teacher);

        context.close();
    }
}
