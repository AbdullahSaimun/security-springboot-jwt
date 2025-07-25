package com.saimun.jwt.config;

import com.saimun.jwt.model.User;
import com.saimun.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		User admin = new User();
		admin.setUsername("1");
		admin.setPassword(passwordEncoder.encode("1"));
		admin.setEmail("1@example.com");
		admin.setFirstName("Admin");
		admin.setLastName("User");
		admin.setRole("ROLE_ADMIN");
		userRepository.save(admin);


	}
}