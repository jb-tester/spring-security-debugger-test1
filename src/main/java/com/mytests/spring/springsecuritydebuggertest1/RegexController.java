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
public class RegexController {

    // "/regex\\d*(/.*)?", role=guest
    @GetMapping("/regex111/test1")
    public ResponseEntity<String> regex111(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("regex111/test1: " + details);
    }
    // "/regex\\d*(/.*)?, role=guest
    @GetMapping("/regex25/test2")
    public ResponseEntity<String> regex2(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("regex25/test2: " + details);
    }
    // "/regex\\d*(/.*)?", role=guest
    @GetMapping("/regex3/test3")
    public ResponseEntity<String> regex3(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("regex3/test3: " + details);
    }

}
