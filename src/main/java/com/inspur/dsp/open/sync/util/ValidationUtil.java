package com.inspur.dsp.open.sync.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtil {
    public static Validator validator;
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static boolean validate(Object object){
        return validator.validate(object).isEmpty();
    }
}
