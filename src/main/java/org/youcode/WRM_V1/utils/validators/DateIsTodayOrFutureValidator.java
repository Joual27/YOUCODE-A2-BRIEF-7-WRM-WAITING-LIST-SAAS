package org.youcode.WRM_V1.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.youcode.WRM_V1.utils.validators.interfaces.DateTodayOrFuture;

import java.time.LocalDate;

public class DateIsTodayOrFutureValidator implements ConstraintValidator<DateTodayOrFuture, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return !date.isBefore(LocalDate.now());
    }
}