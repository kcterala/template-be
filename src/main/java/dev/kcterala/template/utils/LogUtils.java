package dev.kcterala.template.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LogUtils {
    private static final Logger log = LoggerFactory.getLogger(LogUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final String REQUEST_ID_HEADER = "X-Request-Id";
    private static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    private static final String REAL_IP_HEADER = "X-Real-IP";
    private static final int WARN_THRESHOLD = 400;
    private static final int ERROR_THRESHOLD = 500;


    public static void setupMDC(final HttpServletRequest request) {
        final long startTime = System.currentTimeMillis();
        String requestId = request.getHeader(REQUEST_ID_HEADER);
        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }

        MDC.put(LogKey.START_TIME, String.valueOf(startTime));
        MDC.put(LogKey.REQUEST_ID, requestId);
        MDC.put(LogKey.IP_ADDRESS, getClientIp(request));

    }

    public static void logRequestResponse(final HttpServletRequest request,
                                          final HttpServletResponse response) throws JsonProcessingException {
        final long endTime = System.currentTimeMillis();
        final long startTime = Long.parseLong(MDC.get(LogKey.START_TIME));
        final long latency = endTime - startTime;
        final int status = response.getStatus();

        final Map<String, Object> logData = new HashMap<>();
        logData.put(LogKey.IP_ADDRESS, MDC.get(LogKey.IP_ADDRESS));
        logData.put(LogKey.HTTP_METHOD, request.getMethod());
        logData.put(LogKey.END_POINT, request.getRequestURI());
        logData.put(LogKey.STATUS, status);
        logData.put(LogKey.LATENCY_IN_MS, latency);

        if (status >= WARN_THRESHOLD) {
            logData.put(LogKey.EXCEPTION_MESSAGE, MDC.get(LogKey.EXCEPTION_MESSAGE));

            if (status >= ERROR_THRESHOLD) {
                logData.put(LogKey.EXCEPTION_TYPE, MDC.get(LogKey.EXCEPTION_TYPE));
                logData.put(LogKey.EXCEPTION_STACK_TRACE, MDC.get(LogKey.EXCEPTION_STACK_TRACE));
            }
        }

        final String logMessage = objectMapper.writeValueAsString(logData);
        logMessage(status, logMessage);

    }

    public static void logMessage(final int status, final String logMessage) {
        if (status >= ERROR_THRESHOLD) {
            log.error(logMessage);
        } else if (status >= WARN_THRESHOLD) {
            log.warn(logMessage);
        } else {
            log.info(logMessage);
        }
    }

    private static String getClientIp(final HttpServletRequest request) {
        String clientIp = null;
        try {
            final String xForwardedFor = request.getHeader(FORWARDED_FOR_HEADER);
            if (xForwardedFor != null && !xForwardedFor.isBlank()) {
                clientIp = xForwardedFor.split(",")[0].trim();
            }

            if (clientIp == null) {
                clientIp = request.getHeader(REAL_IP_HEADER);
            }

            if (clientIp == null) {
                clientIp = request.getRemoteAddr();
            }
        } catch (final Exception e) {
            log.error("Error while fetching client IP. Error message: {} Error", e.getMessage(), e);
        }

        return clientIp;
    }

}
