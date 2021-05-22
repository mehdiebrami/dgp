package com.dgp.paymentservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardNumberValidator implements ConstraintValidator<CardNumberValidation, String> {

    @Override
    public void initialize(CardNumberValidation mapValidation) {
    }

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("[0-9]{16}");
        Matcher matcher = pattern.matcher(cardNumber.replaceAll("-", ""));
        return matcher.matches();
    }
}
