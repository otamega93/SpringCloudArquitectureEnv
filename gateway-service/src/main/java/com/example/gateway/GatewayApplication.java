package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableZuulProxy
@EnableEurekaClient
// If something goes wrong, comment the @EnableCircuitBreaker. Wrong like a timeout
@EnableCircuitBreaker
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "Gateway");
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Configuration
	@EnableOAuth2Sso
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {	

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			//		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
			//				.authenticated();

			// Testing
			/**
			 * Zuul redirects when above part is on. It redirects based on the
			 * gateway.properties To get this url, go to browser and go to
			 * gatewayUrl:port/login, aka http://localhost:4444/login
			 */

			http
		 		.csrf().disable();
		 
			http
		 		.antMatcher("/**").authorizeRequests().anyRequest()
		 		.permitAll();
		}
	}
}