package dev.kcterala.template.controllers;

import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    private final ApplicationAvailability applicationAvailability;

    public HealthCheckController(final ApplicationAvailability applicationAvailability) {
        this.applicationAvailability = applicationAvailability;
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
