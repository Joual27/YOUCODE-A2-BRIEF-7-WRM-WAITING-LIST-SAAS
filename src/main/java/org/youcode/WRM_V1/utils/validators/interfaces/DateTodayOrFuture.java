package org.youcode.WRM_V1.utils.validators.interfaces;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.youcode.WRM_V1.utils.validators.DateIsTodayOrFutureValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateIsTodayOrFutureValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTodayOrFuture {
    String message() default "Date must be today or in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}