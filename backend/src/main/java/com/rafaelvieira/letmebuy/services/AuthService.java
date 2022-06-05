package com.rafaelvieira.letmebuy.services;


import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.repository.UserRepository;
import com.rafaelvieira.letmebuy.services.handlers.ForbiddenException;
import com.rafaelvieira.letmebuy.services.handlers.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
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

//	public void validateUser(Long userId){
//		User user = authenticated();
//		if(!user.hasRole("ROLE_ADMIN") || !user.hasRole("ROLE_OPERATOR")){
//			throw new ForbiddenException("Access denied");
//		}
//	}
}
