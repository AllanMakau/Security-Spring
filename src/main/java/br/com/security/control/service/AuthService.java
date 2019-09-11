package br.com.security.control.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.security.control.DTO.EmailDTO;
import br.com.security.control.controller.UserNewPasswordDTO;
import br.com.security.control.emailUtil.EmailService;
import br.com.security.control.entity.Conta;
import br.com.security.control.entity.Usuario;
import br.com.security.control.entity.UsuarioTokenRecovery;
import br.com.security.control.repository.ContaRepository;
import br.com.security.control.repository.UsuarioRepository;
import br.com.security.control.repository.UsuarioTokenRecoveryRepository;
import br.com.security.control.security.JwtUtil;
import javassist.NotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AuthService {

	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UsuarioTokenRecoveryRepository usuarioTokenRecoveryRepository;
	
	@Autowired 
	private EmailService emailService;
	
	@Autowired 
	private ContaRepository contaRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	public void sendEmailRecoveryPassword(String Email) throws ObjectNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(Email);
		if(usuario == null) {
			throw new  ObjectNotFoundException("Email não encontrado em nossa base de dados!");
		}
		
		UsuarioTokenRecovery recovery = usuarioTokenRecoveryRepository.findByUsuario(usuario);
		
		if(recovery != null && jwtUtil.tokenValido(recovery.getToken())) {
			throw new  ObjectNotFoundException("Existe um token válido que foi encaminhado para seu email. verifique na sua caixa de entrada ou no spam!");
		}

		String tokenEmailGet = jwtUtil.generateTokenEmailRecovery(usuario.getEmail());
		
		UsuarioTokenRecovery recuva = new UsuarioTokenRecovery();
		recuva.setUsuario(usuario);
		recuva.setToken(tokenEmailGet);
		recuva.setData(new Date());
		
		usuarioTokenRecoveryRepository.save(recuva);
		
		this.enviaEmailTokenRecovery(recuva);
		
	}


	private void enviaEmailTokenRecovery(UsuarioTokenRecovery recuva) {
		
		Conta conta = contaRepository.findByTipoConta("SUPORTE");
		
		EmailDTO email = new EmailDTO();
		email.setAssunto("Recuperação de senha");
		email.setDestinatario(recuva.getUsuario().getEmail());
		email.setTexto("Acesse o link http://localhost:8080/auth/recovery/"+recuva.getToken());
		emailService.sendSimpleMessage(conta, email);
		
	}
	
	public void renewPassword(UserNewPasswordDTO newPasswoed,String token) {
		UsuarioTokenRecovery usuario = usuarioTokenRecoveryRepository.findByToken(token);
		usuario.getUsuario().setSenha(pe.encode(newPasswoed.getConfirmPassword()));
		usuarioRepository.save(usuario.getUsuario());
		usuarioTokenRecoveryRepository.delete(usuario);
	}
	
	
}
