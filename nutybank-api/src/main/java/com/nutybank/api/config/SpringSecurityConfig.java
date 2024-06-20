package com.nutybank.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutybank.api.config.security.JwtAuthenticatorFilter;
import com.nutybank.api.config.security.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticatorFilter jwtAuthenticatorFilter = new JwtAuthenticatorFilter(authenticationManager(), objectMapper);

        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET, "/clients/me").hasAnyRole("CLIENT")
                .requestMatchers(HttpMethod.GET, "/clients").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/clients/client/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.PUT,"/clients/{id}").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.DELETE,"/clients/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/accounts").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/accounts/account/{userName}").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/accounts/account/{id}").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.PUT,"/accounts/deposit/{accountId}").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.DELETE,"/accounts/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/employees/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/transactions").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/transactions//transaction/username/{userName}").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/transactions/transaction/dni/{dni}").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/transactions/transaction/date/{date}").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/api/transactions").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE", "CLIENT")
                .requestMatchers(HttpMethod.DELETE,"/api/transactions/{transactionId}").hasRole("ADMIN")
                .anyRequest().authenticated())
                .addFilter(jwtAuthenticatorFilter)
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
