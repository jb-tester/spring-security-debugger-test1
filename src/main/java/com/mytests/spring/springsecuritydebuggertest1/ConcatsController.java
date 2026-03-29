package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;


@RestController
@RequestMapping("/")
public class ConcatsController {

    @GetMapping("/concat/test1/test")
    public ResponseEntity<String> concatTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("concatenation test1: " + details);

    }
    @GetMapping("/concat/test2/test")
    public ResponseEntity<String> concatTest2(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("concatenation test2: " + details);

    }
}
