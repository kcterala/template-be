package dev.kcterala.template.pojos.responses;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String errorType;
    private final String message;
    private final Object details;

    public ErrorResponse(final int statusCode, final String errorType, final String message, final Object details) {
        this.timestamp = LocalDateTime.now();
        this.status = statusCode;
        this.errorType = errorType;
        this.message = message;
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public Object getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorType() {
        return errorType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
