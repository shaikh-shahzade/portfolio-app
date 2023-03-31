package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.config.jwt.JwtTokenhelper;
import com.portfolio.payload.JwtAuthReq;
import com.portfolio.payload.JwtAuthResponse;

@RestController
@RequestMapping("/user/login")
public class AuthorizationController {

	@Autowired
	AuthenticationManager authManager;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtTokenhelper jwtTokenhelper;
	
	@PostMapping("/v2/auth")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthReq jwtReq) throws Exception
	{
		
		System.out.println(jwtReq.getUsername()+" pass  "+jwtReq.getPassword());
		this.authenticate(jwtReq.getUsername(),jwtReq.getPassword());
		System.out.println("called after auth");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtReq.getUsername());
		String token = jwtTokenhelper.generateToken(userDetails);
		System.out.println("Token " +token);
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.CREATED);
		
	}
	
	private void authenticate(String username , String password) throws Exception
	{
		
		try {
			System.out.println("called");
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS");
		}
		
	}
}
