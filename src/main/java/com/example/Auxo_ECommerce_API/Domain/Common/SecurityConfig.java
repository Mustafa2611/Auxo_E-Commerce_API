package com.example.Auxo_ECommerce_API.Domain.Common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Allow everything for now — lock down later with .authenticated()
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // Disable Spring's built-in form login entirely.
                // We handle login ourselves via fetch() → POST /auth/api/login.
                // Without this, Spring intercepts our POST requests and redirects to /login.
                .formLogin(form -> form.disable())

                // Disable HTTP Basic auth popup in browser
                .httpBasic(basic -> basic.disable())

                // Disable CSRF completely for now — re-enable selectively when you add sessions
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
////@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(
////                                "/", "/home", "/products", "/products/**",
////                                "/uploads/**", "/webjars/**",
////                                "/swagger-ui/**", "/v3/api-docs/**",
////                                "/css/**", "/js/**", "/images/**"
////                        ).permitAll()
////                        .requestMatchers("/admin/**").authenticated()
////                        .anyRequest().permitAll()
////                )
////                .formLogin(form -> form
////                        .loginPage("/admin/login")
////                        .defaultSuccessUrl("/admin/dashboard")
////                        .permitAll()
////                )
////                .logout(logout -> logout.permitAll())
////                .csrf(csrf -> csrf.disable());
////
////        return http.build();
////    }
//}