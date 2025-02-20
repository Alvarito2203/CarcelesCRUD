package com.example.crudusuario.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**").permitAll()            // Permitir acceso a login y recursos estáticos
                .requestMatchers("/admin/**").hasRole("ADMIN")                         // Solo ADMIN puede acceder a /admin/**
                .requestMatchers("/home", "/user/**").hasAnyRole("USER", "ADMIN")      // USER y ADMIN acceden a /home
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    // Redirección personalizada según el rol
                    boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

                    if (isAdmin) {
                        response.sendRedirect("/admin/dashboard");  // ADMIN → dashboard
                    } else {
                        response.sendRedirect("/home");             // USER → home
                    }
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")  // Redirige al login tras cerrar sesión
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/login")  // Redirige a login si se intenta acceder sin permisos
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

