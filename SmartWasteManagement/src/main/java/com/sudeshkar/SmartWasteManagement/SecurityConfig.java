package com.sudeshkar.SmartWasteManagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sudeshkar.SmartWasteManagement.Repository.UserRepository;
import com.sudeshkar.SmartWasteManagement.model.User;

@Configuration
public class SecurityConfig {
	@Bean
    public PasswordEncoder passwordEncoder()throws Exception{
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository repo) {
	    return email -> {
	        User user = repo.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getEmail())
	                .password(user.getPasswordHash())
	                .roles(user.getRole().name())
	                .build();
	    };
	}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwtFilter)throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(java.util.List.of("http://localhost:5173"));  
                    corsConfig.setAllowedMethods(java.util.List.of("GET","POST","PUT","DELETE","OPTIONS"));
                    corsConfig.setAllowedHeaders(java.util.List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                }))
                .authorizeHttpRequests((auth) ->auth
            					.requestMatchers("/api/v1/auth/logout").authenticated()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers(
                                        "/api/v1/citizen/request-otp",
                                        "/api/v1/citizen/verify-otp"
                                    ).permitAll()
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/citizen/**").hasRole("CITIZEN")
                                .anyRequest().authenticated()
                        )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
