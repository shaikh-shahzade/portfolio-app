package com.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/user/create" , "/user/login/v2/auth")
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
