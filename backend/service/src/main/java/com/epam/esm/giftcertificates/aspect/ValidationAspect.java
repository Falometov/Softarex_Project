package com.epam.esm.giftcertificates.aspect;

import com.epam.esm.giftcertificates.exception.MethodArgumentInvalidException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

/**
 * Aspect class for validation dto classes.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Aspect
@Component
public class ValidationAspect {

    private Validator validator;

    @Autowired
    public ValidationAspect(final Validator validator) {
        this.validator = validator;
    }

    /**
     * Method for validation by annotation {@link com.epam.esm.giftcertificates.annotation.Valid}.
     *
     * @param joinPoint value of the object {@link JoinPoint}
     */
    @Before("@annotation(com.epam.esm.giftcertificates.annotation.Valid)")
    public void validate(final JoinPoint joinPoint) {
        Set<ConstraintViolation<Object>> violations;

        violations = validator.validate(Arrays.stream(joinPoint.getArgs()).findFirst().orElse(null));
        if (!CollectionUtils.isEmpty(violations)) {
            throw new MethodArgumentInvalidException(violations.stream().findFirst().get().getMessage());
        }
    }

}
