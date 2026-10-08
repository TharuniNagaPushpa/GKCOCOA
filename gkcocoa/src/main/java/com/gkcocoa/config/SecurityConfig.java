package com.gkcocoa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/product1.html",
                                "/product2.html",
                                "/product3.html",
                                "/cart.html",
                                "/checkout.html",
                                "/order-success.html",
                                "/track-order.html",
                                "/cart.js",
                                "/images/**",
                                "/css/**",
                                "/js/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/orders").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/orders/*").permitAll()

                        .requestMatchers(
                                "/admin.html",
                                "/api/orders/**"
                        ).authenticated()

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .defaultSuccessUrl("/admin.html", true)
                        .permitAll()
                );

        return http.build();
    }
}