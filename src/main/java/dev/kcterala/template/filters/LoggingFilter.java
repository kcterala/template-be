package dev.kcterala.template.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kcterala.template.utils.LogUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public LoggingFilter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    protected void doFilterInternal(final @NotNull HttpServletRequest request,
                                    final @NotNull HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        try {
            LogUtils.setupMDC(request);
            filterChain.doFilter(request, response);
        } finally {
            LogUtils.logRequestResponse(request, response, objectMapper);
            MDC.clear();
        }
    }
}

