package com.fakultet.apigateway.config;

import com.fakultet.apigateway.security.JsonSecurityResponseHandlers;
import com.fakultet.apigateway.security.JwtReactiveAuthenticationManager;
import com.fakultet.apigateway.security.JwtServerAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http,
            JwtServerAuthenticationConverter converter,
            JwtReactiveAuthenticationManager authenticationManager,
            JsonSecurityResponseHandlers jsonSecurityResponseHandlers) {

        AuthenticationWebFilter jwtAuthenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
        jwtAuthenticationWebFilter.setServerAuthenticationConverter(converter);

        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .exceptionHandling(handling -> handling
                    .authenticationEntryPoint(jsonSecurityResponseHandlers)
                    .accessDeniedHandler(jsonSecurityResponseHandlers))
            .authorizeExchange(exchanges -> exchanges

                    .pathMatchers("/auth/**").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/korisnici/registracija").permitAll()
                    .pathMatchers(HttpMethod.POST, "/api/predmeti/**").hasAnyRole("ADMIN", "PROFESOR")
                    .pathMatchers(HttpMethod.POST, "/api/departmani/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.POST, "/api/smerovi/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.PUT, "/api/ispiti/*/ocena/**").hasAnyRole("ADMIN", "PROFESOR")
                    .pathMatchers(HttpMethod.PUT, "/api/zadaci/*/bodovi/**").hasAnyRole("ADMIN", "PROFESOR")
                    .pathMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

                    .anyExchange().authenticated())
            .addFilterAt(jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }
}
