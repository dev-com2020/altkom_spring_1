package com.example.demo;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

}
