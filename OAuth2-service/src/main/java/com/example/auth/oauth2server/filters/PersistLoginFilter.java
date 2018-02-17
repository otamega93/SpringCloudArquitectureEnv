package com.example.auth.oauth2server.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class PersistLoginFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if (null == SecurityContextHolder.getContext().getAuthentication() || SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase("annonymoususer"))
			return;
		
		System.out.println("username: " + SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("principal: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		System.out.println("credentials: " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
		System.out.println("Authorities: " +SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
		System.out.println("response: " + httpResponse.toString());
		
	}



}
