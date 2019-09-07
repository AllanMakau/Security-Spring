package br.com.security.control;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.security.control.entity.Usuario;
import br.com.security.control.repository.UsuarioRepository;

@SpringBootApplication
public class Application  implements CommandLineRunner{

	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
    private BCryptPasswordEncoder encode;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		Usuario user1 = new Usuario("Alan Lima", "alima", encode.encode("12345"), "alan@gmail.com",true);
		Usuario user2 = new Usuario("Fernanda Santos", "fsantos", encode.encode("123321"), "fernanda@gmail.com",true);
		Usuario user3 = new Usuario("Maria julia", "maju", encode.encode("123654"), "maju@gmail.com",true);
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2,user3));
		
	}

}
