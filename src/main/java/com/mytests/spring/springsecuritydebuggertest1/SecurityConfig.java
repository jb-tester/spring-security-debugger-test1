package com.mytests.spring.springsecuritydebuggertest1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String TEST2 = "/test2";
    public static final String CONCAT = "/concat";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String url = "/test1";
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST).hasAnyRole( "ADMIN", "USER")  // ok, except the root mapping - fixed
                        .requestMatchers(HttpMethod.GET,"/").fullyAuthenticated()  // ok, except the root mapping - fixed
                        .requestMatchers("/new_home", "/home").permitAll() // unlock is not required but suggested - fixed
                        .requestMatchers("/anonymous/**").anonymous() // ok
                        .requestMatchers(HttpMethod.GET,"/admin/**").hasAuthority("ROLE_ADMIN") // ok
                        .requestMatchers("/registered/protected/**").hasRole("ADMIN") // ok
                        .requestMatchers("/registered/secured/**").hasRole("USER") // ok
                        .requestMatchers("/registered/multiOr/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST") // ok
                        .requestMatchers("/registered/multiAnd/**").hasAllAuthorities("ROLE_ADMIN", "ROLE_USER") // no unlocking
                        .requestMatchers("/registered/multiAndRoles/**").hasAllRoles("ADMIN", "USER") // no unlocking
                        .requestMatchers("/registered/customAuthorities/**").hasAnyAuthority("FOO", "BAR")
                        .requestMatchers("/{*var}/master1").hasRole("MASTER") // partially detected roots, no unlocking even for detected - unlocking is fixed
                        .requestMatchers("/**/master2").hasRole("MASTER") // ok
                        .requestMatchers("/concat" + url + "/**").hasRole("GUEST")
                        .requestMatchers(CONCAT + TEST2 + "/**").hasRole("GUEST")
                        .requestMatchers("/expression/guest/**").access(new WebExpressionAuthorizationManager("hasRole('GUEST')")) // detected as permitAll, unlocking fails
                        .requestMatchers("/expression/protected/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') || hasRole('USER')")) // detected as permitAll, unlocking fails
                        .requestMatchers("/expression/secured/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') && hasRole('USER')")) // detected as permitAll, unlocking fails
                        .requestMatchers(regexMatcher("/regex\\d*(/.*)?")).hasRole("GUEST") // regexp is not injected; matching endpoints are recognized, but unlocking fails - unlocking is fixed
                        .anyRequest().authenticated()
                );
               // .formLogin(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN").build();
        UserDetails master = User.withDefaultPasswordEncoder()
                .username("master")
                .password("master")
                .roles("MASTER").build();
        UserDetails superuser = User.withDefaultPasswordEncoder()
                .username("super")
                .password("super")
                .roles("ADMIN", "USER").build();
        UserDetails guest = User.withDefaultPasswordEncoder()
                .username("guest")
                .password("guest")
                .roles("GUEST")
                .build();
        UserDetails custom = User.withDefaultPasswordEncoder()
                .username("custom")
                .password("custom")
                .authorities("FOO", "BAR")
                .build();
        return new InMemoryUserDetailsManager(user, admin, guest, master, superuser, custom);
    }
}