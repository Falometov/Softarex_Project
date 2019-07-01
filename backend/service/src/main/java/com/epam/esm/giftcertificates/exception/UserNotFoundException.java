package com.epam.esm.giftcertificates.exception;

import org.springframework.stereotype.Component;

/**
 * Exception for the case when user doesn't exist.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Component
public class UserNotFoundException extends RuntimeException {

    private String errorKey;
    private String username;

    public UserNotFoundException() {
        this.errorKey = null;
        this.username = null;
    }

    public UserNotFoundException(final String errorKey, final String username) {
        this.errorKey = errorKey;
        this.username = username;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getUsername() {
        return username;
    }

}
