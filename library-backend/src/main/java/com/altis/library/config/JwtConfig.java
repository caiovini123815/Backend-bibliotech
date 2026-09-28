package com.altis.library.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtConfig
        implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        Boolean isAdmin = jwt.getClaim("isAdmin");

        if (Boolean.TRUE.equals(isAdmin)) {

            return new JwtAuthenticationToken(
                    jwt,
                    List.of(
                            new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            );
        }

        return new JwtAuthenticationToken(
                jwt,
                List.of(
                        new SimpleGrantedAuthority("ROLE_USER")
                )
        );
    }
}