package com.sudeshkar.SmartWasteManagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Bean
    public PasswordEncoder passwordEncoder()throws Exception{
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return username -> null;   
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwtFilter)throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(java.util.List.of("http://localhost:5173")); // My PWA React app
                    corsConfig.setAllowedMethods(java.util.List.of("GET","POST","PUT","DELETE","OPTIONS"));
                    corsConfig.setAllowedHeaders(java.util.List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                }))
                .authorizeHttpRequests((auth) ->auth
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .anyRequest().authenticated()
                        )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
