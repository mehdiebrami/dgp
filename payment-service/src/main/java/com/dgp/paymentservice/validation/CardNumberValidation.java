package com.dgp.paymentservice.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CardNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumberValidation {
    String message() default "Invalid Card Number! Must be 16 digits.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
