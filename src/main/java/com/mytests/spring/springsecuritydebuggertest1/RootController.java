package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * *
 * <p>Created by Irina on 3/26/2026.</p>
 * *
 */
@RestController
@RequestMapping("/")
public class RootController {

    // isAuthenticated; unlock from controller doesn't work
    @GetMapping()
    public ResponseEntity<String> registeredRoot(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("root that requires authentication: " + details);
    }
}
