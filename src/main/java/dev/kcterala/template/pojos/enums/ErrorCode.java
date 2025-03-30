package dev.kcterala.template.pojos.enums;

public enum ErrorCode {
    BAD_REQUEST("BAD_REQUEST", 400, false),
    UNAUTHENTICATED("UNAUTHENTICATED", 401, false),
    UNAUTHORIZED("UNAUTHORIZED", 403, false),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", 500, true);

    public final String errorType;
    public final int httpStatusCode;
    public final boolean shouldAlert;

    ErrorCode(final String errorType, final int httpStatusCode, final boolean shouldAlert) {
        this.errorType = errorType;
        this.httpStatusCode = httpStatusCode;
        this.shouldAlert = shouldAlert;
    }
}
