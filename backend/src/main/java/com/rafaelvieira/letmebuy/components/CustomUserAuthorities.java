package com.rafaelvieira.letmebuy.components;

import java.util.Collection;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class CustomUserAuthorities {

    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserAuthorities(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

}
