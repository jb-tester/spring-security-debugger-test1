package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;


@RestController
@RequestMapping("/expression")
public class ExpressionsController {

    //"hasRole('GUEST')": detected as permitAll, unlocking fails
    @GetMapping("/guest/test0")
    public ResponseEntity<String> expressionsTest0(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("expressions test0 that requires GUEST role: " + details);
    }
    //"hasRole('ADMIN') || hasRole('USER')": detected as permitAll, unlocking fails
    @GetMapping("/protected/test1")
    public ResponseEntity<String> expressionsTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("expressions test1 that requires USER or ADMIN role: " + details);
    }
    //"hasRole('ADMIN') && hasRole('USER')": detected as permitAll, unlocking fails
    @GetMapping("/secured/test2")
    public ResponseEntity<String> expressionsTest2(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("expressions test2 that requires USER & ADMIN roles: " + details);
    }

}
