package com.saimun.jwt.controller;

import com.saimun.jwt.dto.AuthRequest;
import com.saimun.jwt.dto.AuthResponse;
import com.saimun.jwt.dto.RegisterRequest;
import com.saimun.jwt.model.User;
import com.saimun.jwt.service.RegistrationService;
import com.saimun.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RegistrationService registerUserService;

	@PostMapping("/login")
	public AuthResponse login(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authRequest.getUsername(),
						authRequest.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();
		String jwt = jwtUtil.generateToken(user.getUsername(), user.getRole());

		AuthResponse response = new AuthResponse();
		response.setToken(jwt);
		return response;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		try {
			registerUserService.registerUser(registerRequest);
			return ResponseEntity.ok("User registered successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}