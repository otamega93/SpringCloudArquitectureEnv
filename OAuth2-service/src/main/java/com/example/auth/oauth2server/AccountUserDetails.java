package com.example.auth.oauth2server;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.auth.entities.Account;
import com.example.auth.entities.Authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by informatica on 01/01/16.
 */
public class AccountUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6809181220864736243L;
	private final Account account;

    public AccountUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new GrantedAuthority() {

			private static final long serialVersionUID = 1L;

			@Override
            public String getAuthority() {
            	List<String> authoritiesAsString = new ArrayList<>();
            	for (Authority authorityDB : account.getAuthorities()) {
            		authoritiesAsString.add(authorityDB.getAuthority());
            	}
                return authoritiesAsString.toString();
            }
        };

        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}