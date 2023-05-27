package com.application.letsbuy.internal.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeRange {
        String message() default "Idade mínima deve ser '18'";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
        int minAge() default 18;
}
