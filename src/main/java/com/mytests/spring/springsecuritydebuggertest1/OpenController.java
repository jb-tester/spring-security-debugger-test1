package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/")
public class OpenController {

    // permitAll; no need to unlock, but unlock is suggested
    @GetMapping("/home")
    public ResponseEntity<String> home(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("home endpoint: " + details);
    }
    // permitAll; no need to unlock, but unlock is suggested
    @GetMapping("/new_home")
    public ResponseEntity<String> root(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("new_home endpoint: " + details);
    }


}
