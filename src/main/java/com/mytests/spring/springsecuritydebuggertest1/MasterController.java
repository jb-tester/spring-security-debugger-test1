package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/")
public class MasterController {

    // "/{*var}/master1", hasRole("MASTER"); unlocking fails
    @GetMapping("/foo/master1")
    public ResponseEntity<String> fooMaster(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("foo/master1: " + details);
    }
    // "/{*var}/master1", hasRole("MASTER"); unlocking fails
    @GetMapping("/foo/test/master1")
    public ResponseEntity<String> fooTestMaster(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("foo/test/master1: " + details);
    }
    //"/**/master2", hasRole("MASTER"); unlocking works
    @GetMapping("/bar/test/master2")
    public ResponseEntity<String> barMaster(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("bar/test/master2: " + details);
    }



}
