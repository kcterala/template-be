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

@RestController
@RequestMapping("/health")
@Tag(name = "Health API", description = "Application Monitoring APIs")
public class HealthCheckController {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckController.class);
    private final ApplicationAvailability applicationAvailability;

    public HealthCheckController(final ApplicationAvailability applicationAvailability) {
        this.applicationAvailability = applicationAvailability;
    }

    @GetMapping
    public ResponseEntity<String> test() throws TemplateException {
        log.error("Sorry not implemented yet");
        throw new TemplateException(ErrorCode.INTERNAL_SERVER_ERROR, "Test Exception");
    }

    @GetMapping("/livenessState")
    public ResponseEntity<String> getLivenessState() {
        return ResponseEntity.ok(applicationAvailability.getLivenessState().name());
    }

    @GetMapping("/readinessState")
    public ResponseEntity<String> getReadinessState() {
        return ResponseEntity.ok(applicationAvailability.getReadinessState().name());
    }
}
