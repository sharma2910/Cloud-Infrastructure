package com.cloud.assignment1_server;

import com.cloud.assignment1_server.services.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(webSecurityDebug);
    }

    @Autowired
    SecurityConfig(
            UserService userService,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(this.userService).passwordEncoder(bCryptPasswordEncoder);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers("/api/v1/healthz").authenticated();
                            request.anyRequest().permitAll();
                        }
                ).authenticationManager(authenticationManager)
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
