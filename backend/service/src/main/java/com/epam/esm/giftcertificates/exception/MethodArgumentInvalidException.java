package com.epam.esm.giftcertificates.exception;

import org.springframework.stereotype.Component;

/**
 * Exception for the case if method argument is invalid.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Component
public class MethodArgumentInvalidException extends RuntimeException {

    private final String errorCode;

    public MethodArgumentInvalidException() {
        this.errorCode = null;
    }

    public MethodArgumentInvalidException(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
