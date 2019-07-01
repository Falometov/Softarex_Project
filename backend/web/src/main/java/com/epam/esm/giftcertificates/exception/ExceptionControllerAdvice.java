package com.epam.esm.giftcertificates.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for exceptions.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final String NOT_NULL_REQUEST_BODY = "NotNull.requestBody";
    private static final String INVALID_VALUE_ID = "InvalidValue.id";
    private static final String ERROR_CODE_NOT_FOUND = "4000";
    private static final String ERROR_CODE_ALREADY_EXIST = "4001";
    private static final String ERROR_CODE_ARGUMENT_INCORRECT = "4002";
    private static final String ERROR_CODE_REQUEST_BODY = "4003";
    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
    private ResourceBundleMessageSource messageSource;

    @Autowired
    public ExceptionControllerAdvice(final ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Exception handler for exception {@link EntityNotFoundException}.
     *
     * @param ex      value of the object {@link EntityNotFoundException}
     * @param request value of the object {@link HttpServletRequest}
     * @return value of the object {@link ResponseEntity}
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException(final EntityNotFoundException ex,
                                                                      final HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorCode(ERROR_CODE_NOT_FOUND);
        exceptionDto.setErrorMessage(messageSource.getMessage(ex.getErrorKey(),
                new Object[]{ex.getId()}, request.getLocale()));
        logger.error(exceptionDto.getErrorMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for exception {@link UserNotFoundException}.
     *
     * @param ex      value of the object {@link UserNotFoundException}
     * @param request value of the object {@link HttpServletRequest}
     * @return value of the object {@link ResponseEntity}
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(final UserNotFoundException ex,
                                                                    final HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorCode(ERROR_CODE_NOT_FOUND);
        exceptionDto.setErrorMessage(messageSource.getMessage(ex.getErrorKey(),
                new Object[]{ex.getUsername()}, request.getLocale()));
        logger.error(exceptionDto.getErrorMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for exception {@link EntityAlreadyExistException}.
     *
     * @param ex      value of the object {@link EntityAlreadyExistException}
     * @param request value of the object {@link HttpServletRequest}
     * @return value of the object {@link ResponseEntity}
     */
    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ExceptionDto> handleEntityAlreadyExistException(final EntityAlreadyExistException ex,
                                                                          final HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorCode(ERROR_CODE_ALREADY_EXIST);
        exceptionDto.setErrorMessage(messageSource.getMessage(ex.getErrorKey(),
                new Object[]{ex.getName()}, request.getLocale()));
        logger.error(exceptionDto.getErrorMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for exception {@link MethodArgumentInvalidException}.
     *
     * @param ex      value of the object {@link MethodArgumentInvalidException}
     * @param request value of the object {@link HttpServletRequest}
     * @return value of the object {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentInvalidException.class)
    public ResponseEntity<ExceptionDto> handleMethodArgumentInvalidException(
            final MethodArgumentInvalidException ex, final HttpServletRequest request) {
        ExceptionDto exception = new ExceptionDto();
        exception.setErrorCode(ERROR_CODE_ARGUMENT_INCORRECT);
        exception.setErrorMessage(messageSource.getMessage(ex.getErrorCode(), null, request.getLocale()));
        logger.error(exception.getErrorMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for exception {@link HttpMessageNotReadableException}.
     *
     * @param ex      value of the object {@link HttpMessageNotReadableException}
     * @param request value of the object {@link HttpServletRequest}
     * @return value of the object {@link ResponseEntity}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> handleHttpMessageNotReadableException(
            final HttpMessageNotReadableException ex, final HttpServletRequest request) {
        ExceptionDto exception = new ExceptionDto();
        exception.setErrorCode(ERROR_CODE_REQUEST_BODY);
        exception.setErrorMessage(messageSource.getMessage(NOT_NULL_REQUEST_BODY, null, request.getLocale()));
        logger.error(exception.getErrorMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for exception {@link InvalidRequestParameterException}.
     *
     * @param ex      value of the object {@link InvalidRequestParameterException}
     * @param request value of the objcect {@link HttpServletRequest}
     * @return value of the object {@link ResponseEntity}
     */
    @ExceptionHandler(InvalidRequestParameterException.class)
    public ResponseEntity<ExceptionDto> handleInvalidRequestParameterException(
            final InvalidRequestParameterException ex, final HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorCode(ERROR_CODE_ARGUMENT_INCORRECT);
        exceptionDto.setErrorMessage(messageSource.getMessage(ex.getErrorKey(), null, request.getLocale()));
        logger.error(exceptionDto.getErrorMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for exception {@link MethodArgumentTypeMismatchException}.
     *
     * @param ex      value of the object {@link MethodArgumentTypeMismatchException}
     * @param request value of the object {@link HttpServletRequest}
     * @return value of the object {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDto> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException ex, final HttpServletRequest request) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorCode(ERROR_CODE_ARGUMENT_INCORRECT);
        exceptionDto.setErrorMessage(messageSource.getMessage(INVALID_VALUE_ID, null, request.getLocale()));
        logger.error(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

}
