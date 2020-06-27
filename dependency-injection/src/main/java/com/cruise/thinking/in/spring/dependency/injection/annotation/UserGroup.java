package com.cruise.thinking.in.spring.dependency.injection.annotation;

import com.cruise.thinking.in.spring.dependency.injection.QualifierDependencyInjectionDemo;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 描述：基于 {@link Qualifier} 进行扩展
 * <p>
 * 由于 {@link Qualifier} 支持在注解上使用 所以可以自定义注解继承  {@link Qualifier} 进行扩展
 * 如：Spring Cloud 的 @LoadBalanced
 * </p>
 *
 * @author Cruise
 * @version 1.0
 * @see QualifierDependencyInjectionDemo
 * @since 2020/6/27
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {
}
