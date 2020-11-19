package com.stage.stage.security.jwt;

public class AuthenticationResponse {

    private String jwt ;
    private String expirationDate ;

    public AuthenticationResponse(String jwt , String expirationDate) {
        this.jwt = jwt;
        this.expirationDate = expirationDate ;
    }

    public String getJwt() {
        return jwt;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
