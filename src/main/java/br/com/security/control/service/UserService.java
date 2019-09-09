package br.com.security.control.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.security.control.security.UserSS;

public class UserService {

	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
