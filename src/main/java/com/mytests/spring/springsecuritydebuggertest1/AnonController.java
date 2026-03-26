package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/")
public class AnonController {

    // anonymous; no need to unlock, unlock is not suggested
    @GetMapping("/anonymous/test1")
    public ResponseEntity<String> anonTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("anonymous test1: " + details);

    }
    // anonymous; no need to unlock, unlock is not suggested
    @GetMapping("/anonymous/foo/test2")
    public ResponseEntity<String> anonTest2(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("anonymous test2: " + details);

    }
}

