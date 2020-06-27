package com.cruise.thinking.in.spring.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/27
 */
@Target({ ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Reference {
}
