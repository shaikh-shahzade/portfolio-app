package com.portfolio.config.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	JwtTokenhelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestToken = request.getHeader("Authorization");
		String username = null;
		String token =null;
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			token = requestToken.substring(7);
			try {
				username = jwtTokenHelper.getUsernameFromToken(token);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error with token");
			}
		}
		else
		{
			System.out.println("INVALID TOKEN");
		}
			if(username!=null &&SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userdetails = userDetailService.loadUserByUsername(username);
					if(this.jwtTokenHelper.validateToken(token, userdetails))
					{
						System.out.println("First Check : " + username);
						
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userdetails,null,userdetails.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(
								new WebAuthenticationDetailsSource()
								.buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						
					}
			}
			else
			{
				System.out.println("Invalid Request");
			}
			
			filterChain.doFilter(request, response);
			
		
		
		
		
		
	}

}
