package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.repository.UserRepository;
import com.rafaelvieira.letmebuy.services.email.EmailService;
import com.rafaelvieira.letmebuy.services.handlers.ForbiddenException;
import com.rafaelvieira.letmebuy.services.handlers.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author rafae
 */
@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;
	private final Random rand = new Random();

	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);
		}
		catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if (!user.getId().equals(userId) && !user.hasRole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied");
		}
	}

	@Transactional
	public void sendNewPassword(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ForbiddenException("Email não encontrado");
		}
		String newPass = newPassword();
		user.setPassword(passwordEncoder.encode(newPass));
		userRepository.save(user);
		emailService.sendNewPasswordEmail(user, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		}
		else {
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
