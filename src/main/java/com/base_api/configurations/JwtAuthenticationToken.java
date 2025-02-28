package com.base_api.configurations;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken  extends UsernamePasswordAuthenticationToken {

    private final String externalId;

    public JwtAuthenticationToken(UserDetails principal, Object credentials, String externalId, Collection authorities) {
        super(principal, credentials, authorities);
        this.externalId = externalId;
    }

}