package com.epam.esm.giftcertificates.exception;

/**
 * Exception for the case if request parameter is incorrect.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public class InvalidRequestParameterException extends RuntimeException {

    private String errorKey;

    public InvalidRequestParameterException() {
        super();
        errorKey = null;
    }

    public InvalidRequestParameterException(final String errorKey) {
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return errorKey;
    }

}
