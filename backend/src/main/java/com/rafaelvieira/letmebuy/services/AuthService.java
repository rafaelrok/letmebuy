package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.dto.EmailDTO;
import com.rafaelvieira.letmebuy.dto.NewPasswordDTO;
import com.rafaelvieira.letmebuy.entities.PasswordRecover;
import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.repository.PasswordRecoverRepository;
import com.rafaelvieira.letmebuy.repository.UserRepository;
import com.rafaelvieira.letmebuy.services.email.EmailService;
import com.rafaelvieira.letmebuy.services.handlers.ForbiddenException;
import com.rafaelvieira.letmebuy.services.handlers.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author rafae
 */
@Service
public class AuthService {

	@Value("${spring.mail.username}")
	private String defaultSender;

	@Value("${email.password-recover.uri}")
	private String recoverUri;

	@Value("${email.password-recover.token.minutes}")
	private Long tokenMinutes;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final PasswordRecoverRepository passwordRecoverRepository;
	private final EmailService emailService;
	private final Random rand = new Random();

	public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService, PasswordRecoverRepository passwordRecoverRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.passwordRecoverRepository = passwordRecoverRepository;
	}

	@Transactional
	public void createRecoverToken(EmailDTO body) {
		User user = userRepository.findByEmail(body.getEmail());
		if (user == null) {
			throw new ResourceNotFoundException("Email not found");
		}

		String token = UUID.randomUUID().toString();

		PasswordRecover entity = new PasswordRecover();
		entity.setToken(token);
		entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
		entity.setEmail(body.getEmail());
		passwordRecoverRepository.save(entity);

		String text = "Acesse o link para definir uma nova senha (válido por " + tokenMinutes + " minutos):\n\n"
				+ recoverUri + token;

		emailService.sendRecoverPassword(body.getEmail(), "Recuperação de senha", text);
	}

	@Transactional
	public void saveNewPassword(NewPasswordDTO body) {
		List<PasswordRecover> list = passwordRecoverRepository.searchValidTokens(body.getToken(), Instant.now());

		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Invalid token");
		}

		User user = userRepository.findByEmail(list.get(0).getEmail());
		if (user == null) {
			throw new ResourceNotFoundException("User not found");
		}
		user.setPassword(passwordEncoder.encode(body.getPassword()));
		userRepository.save(user);
	}

	protected User authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return userRepository.findByEmail(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Invalid user");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if (!user.getId().equals(userId) && user.hasRole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied");
		}
	}

//	@Transactional
//	public void sendNewPassword(String email) {
//		User user = userRepository.findByEmail(email);
//		if (user == null) {
//			throw new ForbiddenException("Email não encontrado");
//		}
//		String newPass = newPassword();
//		user.setPassword(passwordEncoder.encode(newPass));
//		userRepository.save(user);
//		emailService.sendNewPasswordEmail(user, newPass);
//	}
//
//	private String newPassword() {
//		char[] vet = new char[10];
//		for (int i=0; i<10; i++) {
//			vet[i] = randomChar();
//		}
//		return new String(vet);
//	}
//
//	private char randomChar() {
//		int opt = rand.nextInt(3);
//		if (opt == 0) {
//			return (char) (rand.nextInt(10) + 48);
//		}
//		else if (opt == 1) {
//			return (char) (rand.nextInt(26) + 65);
//		}
//		else {
//			return (char) (rand.nextInt(26) + 97);
//		}
//	}

}
