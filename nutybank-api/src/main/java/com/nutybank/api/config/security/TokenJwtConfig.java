package com.nutybank.api.config.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class TokenJwtConfig {

    protected static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    protected static final String TOKEN_PREFIX = "Bearer ";
    protected static final String HEADER_AUTHORIZATION = "Authorization";
    protected static final String CONTENT_TYPE= "application/json";
}
