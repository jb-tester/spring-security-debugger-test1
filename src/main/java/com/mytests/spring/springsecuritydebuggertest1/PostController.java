package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/")
public class PostController {
    //hasAnyRole( "ADMIN", "USER"); unlock doesn't work - root mapping
    @PostMapping()
    public ResponseEntity<String> postRoot(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("root post: " + body + ", authentication: " + details);
    }
    //hasAnyRole( "ADMIN", "USER"); unlock works
    @PostMapping("/home")
    public ResponseEntity<String> postHome(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("home post: " + body + ", authentication: " + details);
    }
    //hasRole(admin); unlock works
    @PostMapping("/foo")
    public ResponseEntity<String> postFoo(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("foo post: " + body + ", authentication: " + details);
    }
}
