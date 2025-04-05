package dev.kcterala.template.controllers;

import dev.kcterala.template.exceptions.TemplateException;
import dev.kcterala.template.pojos.enums.ErrorCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/health")
@Tag(name = "Health API", description = "Application Monitoring APIs")
public class HealthCheckController {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckController.class);
    private final ApplicationAvailability applicationAvailability;

    public HealthCheckController(final ApplicationAvailability applicationAvailability) {
        this.applicationAvailability = applicationAvailability;
    }

    /**
     * Handles GET requests to the /health endpoint.
     *
     * <p>This placeholder endpoint logs an error and always throws a TemplateException with an internal
     * server error code to indicate that the functionality is not implemented.
     *
     * @throws TemplateException always thrown to signal that the endpoint is not yet implemented
     */
    @GetMapping
    public ResponseEntity<String> test() throws TemplateException {
        log.error("Sorry not implemented yet");
        throw new TemplateException(ErrorCode.INTERNAL_SERVER_ERROR, "Test Exception");
    }

    /**
     * Retrieves the application's current liveness state.
     *
     * <p>This endpoint handles GET requests to "/health/livenessState" and returns the liveness state as a plain text response.
     * The state is determined using the application's availability component.
     *
     * @return a ResponseEntity containing the liveness state's name as a plain text string.
     */
    @GetMapping(value = "/livenessState", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getLivenessState() {
        return ResponseEntity.ok(applicationAvailability.getLivenessState().name());
    }

    /**
     * Retrieves the application's readiness state.
     *
     * <p>This endpoint returns the current readiness state as plain text, indicating whether the application is ready to accept requests.</p>
     *
     * @return a ResponseEntity containing the readiness state as a string with an HTTP 200 OK status.
     */
    @GetMapping(value = "/readinessState", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getReadinessState() {
        return ResponseEntity.ok(applicationAvailability.getReadinessState().name());
    }
}
