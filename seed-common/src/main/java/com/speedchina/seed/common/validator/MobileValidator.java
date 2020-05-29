package com.speedchina.seed.common.validator;


import com.speedchina.seed.common.annotation.IsMobile;
import com.speedchina.seed.common.entity.RegexpConstant;
import com.speedchina.seed.common.utils.SeedUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return SeedUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
