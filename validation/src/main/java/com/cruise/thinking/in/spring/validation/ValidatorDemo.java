package com.cruise.thinking.in.spring.validation;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.Locale;

import static com.cruise.thinking.in.spring.validation.ErrorsMessageDemo.createMessageSource;

/**
 * 自定义 {@link Validator} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Validator
 * @since 2020/7/8
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        CustomizedValidator validator = new CustomizedValidator();
        User user = new User();
        // 判断是否支持 User 类型，这个框架会自动验证不需要手动调
        boolean supports = validator.supports(user.getClass());
        System.out.println(supports);
        user.setName("Cruise");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        MessageSource messageSource = createMessageSource();

        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class CustomizedValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
            String name = user.getName();
        }
    }
}
