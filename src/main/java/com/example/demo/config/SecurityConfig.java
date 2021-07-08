package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final List<String> LIST_METHOD_ALLOW = Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE");

    @Bean
    public BCryptPasswordEncoder
    bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public SecurityConfig() {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
//        http
//                .cors()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**")
////                .hasAnyAuthority("admin")
////                .antMatchers("/login/**")
////                .hasAnyAuthority("dac")
////                .antMatchers( "/login/**")
////                .hasAnyAuthority("advertiser")
////                .anyRequest()
//                .permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(LIST_METHOD_ALLOW);
        corsConfiguration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin",
                "Cache-Control", "Content-Type"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
