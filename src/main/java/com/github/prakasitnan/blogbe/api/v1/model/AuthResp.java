package com.github.prakasitnan.blogbe.api.v1.model;

public class AuthResp {

    private Long id;
    private String username;
    private String jwt;

    public AuthResp(Long id, String username, String jwt) {
        this.id = id;
        this.username = username;
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
