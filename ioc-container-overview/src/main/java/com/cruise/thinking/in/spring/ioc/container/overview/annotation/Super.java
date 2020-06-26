package com.cruise.thinking.in.spring.ioc.container.overview.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * 超级用户的注解
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Super {
}
