package com.epam.esm.giftcertificates.exception;

/**
 * Dto class for exceptions.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public class ExceptionDto {

    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
