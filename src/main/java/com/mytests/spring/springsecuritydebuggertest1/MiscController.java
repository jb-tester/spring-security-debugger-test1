package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/misc")
public class MiscController {

    @GetMapping("/pathvars/{pv}")
    public String pathVars(@PathVariable String pv) {
        return "test pathvars " + pv;
    }

    @GetMapping("/reqparams/test")
    public String params(@RequestParam String param1, @RequestParam String param2) {
        return "test request params " + param1 + ", " + param2;
    }

    @GetMapping("/params/testPrincipal")
    public String paramsPrincipal(Principal principal) {
        return "test params: principal name = " + principal.getName();
    }
    
    @GetMapping("/params/testAuthentication")
    public String paramsAuthentication(Authentication authentication) {
        authentication.getName();
        return "test params: authentication  name = " + authentication.getName();
    }

    // npe on unlocked running
    @GetMapping("/params/testAuthPrincipalAnno")
    public String method(@AuthenticationPrincipal UserDetails userDetails) {
        return "test params: authenticatedprincipal name = " + userDetails.getUsername();
    }
    @GetMapping("/params/currentSecContextExprTest")
    public String method(@CurrentSecurityContext(expression="authentication") Authentication auth) {
        return "test params: currentSecurityContext expr: " + auth.getPrincipal() + ", " + auth.getCredentials() + ", " + auth.getAuthorities();
         }
}
