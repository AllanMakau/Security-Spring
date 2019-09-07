package br.com.security.control;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.security.control.entity.Funcionalidade;
import br.com.security.control.entity.Perfil;
import br.com.security.control.entity.Usuario;
import br.com.security.control.repository.FuncionalidadeRepository;
import br.com.security.control.repository.PerfilRespository;
import br.com.security.control.repository.UsuarioRepository;

@SpringBootApplication
public class Application  implements CommandLineRunner{

	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PerfilRespository perfilRepository;
	
	@Autowired
	FuncionalidadeRepository funcionalidadeReposytory;
	
	@Autowired
    private BCryptPasswordEncoder encode;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		Funcionalidade f1 = new Funcionalidade(null, "cadastra", "cad", true);
		Funcionalidade f2 = new Funcionalidade(null, "consulta", "con", true);
		Funcionalidade f3 = new Funcionalidade(null, "atualiza", "atu", true);
		Funcionalidade f4 = new Funcionalidade(null, "deleta", "del", true);
		
		funcionalidadeReposytory.saveAll(Arrays.asList(f1,f2,f3,f4));
		
		Perfil p1 = new Perfil(null, "Assistente", "Assistente", true);
		Perfil p2 = new Perfil(null, "Atendente", "Atendente", true);
		Perfil p3 = new Perfil(null, "Supervisor", "Supervisor", true);
		Perfil p4 = new Perfil(null, "Coordenador", "Coordenador", true);
		
		perfilRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
		
		
		Usuario user1 = new Usuario("Alan Lima", "alima", encode.encode("12345"), "alan@gmail.com",true);
		Usuario user2 = new Usuario("Fernanda Santos", "fsantos", encode.encode("123321"), "fernanda@gmail.com",true);
		Usuario user3 = new Usuario("Maria julia", "maju", encode.encode("123654"), "maju@gmail.com",true);
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2,user3));
		
	}

}
