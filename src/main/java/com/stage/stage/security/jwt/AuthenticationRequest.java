package com.stage.stage.security.jwt;

public class AuthenticationRequest {

    private String username ;
    private String password ;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
