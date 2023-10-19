package com.example.demo;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authoritites = new ArrayList<>();

    protected UserAccount() {
    }

    public UserAccount(String username, String password, String... authoritites) {
        this.username = username;
        this.password = password;
        this.authoritites = Arrays.stream(authoritites)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }

    public UserDetails asUser(){
        return User.withDefaultPasswordEncoder()
                .username(getUsername())
                .password(getPassword())
                .authorities(getAuthoritites())
                .build();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAuthoritites() {
        return authoritites;
    }

    public void setAuthoritites(List<GrantedAuthority> authoritites) {
        this.authoritites = authoritites;
    }
}
