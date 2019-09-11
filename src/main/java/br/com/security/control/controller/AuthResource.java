package br.com.security.control.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.control.DTO.EmailUserDTO;
import br.com.security.control.security.JwtUtil;
import br.com.security.control.security.UserSS;
import br.com.security.control.service.AuthService;
import br.com.security.control.service.UserService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> resfreshToken(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String  token = jwtUtil.generateToken(user);
		response.addHeader("Authorization","Bearer "+token);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value = "/recoveryGenerateToken", method = RequestMethod.POST)
	public ResponseEntity<Void> recoveryGenerateToken( @RequestBody EmailUserDTO email) throws ObjectNotFoundException{
		authService.sendEmailRecoveryPassword(email.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/newPassword/{token}", method = RequestMethod.POST)
	public ResponseEntity<Void> generatePassword( @RequestBody UserNewPasswordDTO newPassword,@PathVariable String token) throws ObjectNotFoundException{
		authService.renewPassword(newPassword,token);
		return ResponseEntity.noContent().build();
	}
	
	
} 
