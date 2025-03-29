package dev.kcterala.template.exceptions;

public class TemplateException extends Exception {
    public ErrorCode errorCode;
    public Object details;

    public TemplateException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TemplateException(final ErrorCode errorCode, final String message, final Object details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }
}
