package com.saimun.jwt.dto;

import lombok.Data;

@Data
public class AuthRequest {
	private String username;
	private String password;
}
