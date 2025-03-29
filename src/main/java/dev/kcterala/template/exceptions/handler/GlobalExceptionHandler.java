package dev.kcterala.template.exceptions.handler;

import dev.kcterala.template.exceptions.TemplateException;
import dev.kcterala.template.pojos.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "An unexpected error occurred";
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String errorType = INTERNAL_SERVER_ERROR;
        String message = INTERNAL_SERVER_ERROR_MESSAGE;
        Object details = null;


        if (exception instanceof final TemplateException templateException) {
            statusCode = templateException.errorCode.httpStatusCode;
            errorType = templateException.errorCode.errorType;
            message = templateException.getMessage();
            details = templateException.details;
        } else if (exception instanceof final NoResourceFoundException noResourceFoundException) {
            statusCode = HttpStatus.NOT_FOUND.value();
            errorType = "NOT_FOUND";
            message = noResourceFoundException.getMessage();
        } else {
            log.error(exception.getMessage(), exception);
        }

        final ErrorResponse errorResponse = new ErrorResponse(statusCode, errorType, message, details);
        return ResponseEntity.status(statusCode).body(errorResponse);
    }
}
