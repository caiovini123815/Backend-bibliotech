package com.altis.library.config;

import org.springframework.http.HttpMethod;
import org.springframework.core.convert.converter.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   Converter<Jwt,AbstractAuthenticationToken> jwtAuthenticationConverter)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reset-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()



                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()


                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")


                        .requestMatchers(HttpMethod.GET, "/books/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")


                        .requestMatchers(HttpMethod.GET, "/publishers/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/publishers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/publishers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/publishers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/publishers/**").hasRole("ADMIN")




                        .requestMatchers(HttpMethod.GET, "/rents/me").authenticated()

                        .requestMatchers(HttpMethod.GET, "/rents/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/rents/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/rents/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/rents/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/rents/**").hasRole("ADMIN")




                        .requestMatchers(HttpMethod.GET, "/dashboard/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/dashboard/user").authenticated()


                        .anyRequest().authenticated()

                ).oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));

        return http.build();
    }

}
