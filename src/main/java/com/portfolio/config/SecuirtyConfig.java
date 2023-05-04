package com.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.portfolio.config.jwt.JwtAuthenticationFilter;

@Configuration
public class SecuirtyConfig {

	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;
	
	String publicURLs[] = { "/user/create" , "/user/login/v2/auth" , 
			"/swagger-resources/**",
	        "/swagger-ui/**","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
	        "/v3/api-docs","/context-path/**","/context-path/swagger-ui.html",
	        "/context-path/v3/api-docs",
	        "/webjars/**" , "/v2/api-docs"};
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers(publicURLs)
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			httpSecurity
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		return httpSecurity.build();
	}
	
	
}
