package com.neocyber.security.jwt;

import java.io.Serializable;

public record AuthenticationResponse(String jwt) implements Serializable {
    public String getJwt() {
        return jwt;
    }
}
