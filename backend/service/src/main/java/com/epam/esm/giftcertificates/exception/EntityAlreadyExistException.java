package com.epam.esm.giftcertificates.exception;

import org.springframework.stereotype.Component;

/**
 * Exception for the case if entity already exist.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Component
public class EntityAlreadyExistException extends RuntimeException {

    private final String errorKey;
    private final String name;

    public EntityAlreadyExistException() {
        errorKey = null;
        name = null;
    }

    public EntityAlreadyExistException(final String errorKey, final String name) {
        this.errorKey = errorKey;
        this.name = name;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getName() {
        return name;
    }

}
