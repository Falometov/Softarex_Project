package com.epam.esm.giftcertificates.exception;

import org.springframework.stereotype.Component;

/**
 * Exception for the case if entity object doesn't exist.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Component
public class EntityNotFoundException extends RuntimeException {

    private final String errorKey;
    private final Long id;

    public EntityNotFoundException() {
        errorKey = null;
        id = null;
    }

    public EntityNotFoundException(final String errorKey, final Long id) {
        this.errorKey = errorKey;
        this.id = id;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public Long getId() {
        return id;
    }

}
