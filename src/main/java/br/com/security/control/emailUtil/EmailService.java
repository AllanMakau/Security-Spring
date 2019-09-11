package br.com.security.control.emailUtil;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import br.com.security.control.DTO.EmailDTO;
import br.com.security.control.entity.Conta;


@Service
public class EmailService  {



	@Autowired
    public JavaMailSender emailSender;
	@Autowired
	public JavaMailSenderImpl emailSenderImpl;
	
		
	public void sendSimpleMessage(Conta conta, EmailDTO email) {
		       
				emailSenderImpl.setUsername(conta.getLogin());
				emailSenderImpl.setPassword(conta.getSenha());
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setTo(email.getDestinatario()); 
		        message.setSubject(email.getAssunto()); 
		        message.setText(email.getTexto());
		        emailSender.send(message);
		     
		    }
    
}
