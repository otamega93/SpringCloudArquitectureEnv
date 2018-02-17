package com.example.auth.oauth2server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.auth.entities.Account;
import com.example.auth.entities.Authority;
import com.example.auth.repositories.AccountRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by informatica on 01/01/16.
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("no user found with username: " + username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Authority authority : account.getAuthorities()) {
        	authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}
        
        return new User(account.getUsername(), account.getPassword(), account.getIsActive(), true, true, account.getIsActive(), authorities);
    }
}
