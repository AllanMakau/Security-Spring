package br.com.security.control.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.control.security.JwtUtil;
import br.com.security.control.security.UserSS;
import br.com.security.control.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> resfreshToken(HttpServletResponse response){
		
		UserSS user = UserService.authenticated();
		String  token = jwtUtil.generateToken(user);
		response.addHeader("Authorization","Bearer "+token);
		return ResponseEntity.noContent().build();
	}
} 
