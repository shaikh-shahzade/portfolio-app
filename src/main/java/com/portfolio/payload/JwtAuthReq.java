package com.portfolio.payload;

import lombok.Data;

@Data
public class JwtAuthReq {

	private String username;
	private String password;
}
