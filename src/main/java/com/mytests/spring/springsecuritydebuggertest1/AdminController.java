package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/admin")
public class AdminController {
    // hasAuthority("ROLE_ADMIN"), unlock works
    @GetMapping("/test1")
    public ResponseEntity<String> adminTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("admin test1: " + details);
    }

    // hasAuthority("ROLE_ADMIN"), unlock works
    @GetMapping()
    public ResponseEntity<String> adminRoot(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("admin root: " + details);
    }

}
