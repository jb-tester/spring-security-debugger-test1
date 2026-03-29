package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/registered")
public class RegisteredController {
    //hasRole(admin); unlock works
    @GetMapping("/protected/test1")
    public ResponseEntity<String> registeredTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("registered test1 that requires ADMIN role: " + details);
    }
    // hasRole(user); unlock works
    @GetMapping("/secured/test2")
    public ResponseEntity<String> registeredTest2(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("registered test2 that requires USER role: " + details);
    }
    // hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST")
    @GetMapping("/multiOr/test3")
    public ResponseEntity<String> registeredTest3(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("registered test3 that requires USER/ADMIN/GUEST role: " + details);
    }
    // hasAllAuthorities("ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST")
    @GetMapping("/multiAnd/test4")
    public ResponseEntity<String> registeredTest4(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("registered test4 that requires USER & ADMIN roles: " + details);
    }
    // isAuthenticated; unlock works
    @GetMapping()
    public ResponseEntity<String> registeredRoot(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("registered root that requires authentication: " + details);
    }
}
