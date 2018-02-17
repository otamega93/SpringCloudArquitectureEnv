package com.example.auth.oauth2server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.example.auth.oauth2server.filters.CustomCorsFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by on 28.01.16.
 *
 * @author David Steiman
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
    
    @Autowired
    private UserDetailsService UserDetailServiceImpl;
    
    @Autowired
    private CustomCorsFilter corsFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
    		.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
    	
		// @formatter:off
		http
			.csrf().disable()
			.formLogin().loginPage("/login").permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/oauth/authorize", "/oauth/confirm_access").authenticated()
			.antMatchers("/oauth/token", "/oauth/clients", "/auth/oauth/token", "/auth/oauth/clients").permitAll();
		// @formatter:on

    }

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(UserDetailServiceImpl);
		//daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;

	}

	@Autowired
	public void configAuthBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());

	}
    
}
