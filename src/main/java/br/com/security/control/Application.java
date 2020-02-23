package br.com.security.control;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.security.control.entity.Conta;
import br.com.security.control.entity.Funcionalidade;
import br.com.security.control.entity.Perfil;
import br.com.security.control.entity.Usuario;
import br.com.security.control.repository.ContaRepository;
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
	ContaRepository contaRepository;
	
	@Autowired
    private BCryptPasswordEncoder encode;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*
		Conta conta = new Conta(null,"SUPORTE", "suportehotelpiramide@gmail.com", "hotelpiramide1", 587, "smtp.gmail.com");
		
		contaRepository.save(conta);

		Funcionalidade f1 = new Funcionalidade("cadastra", "cad", true);
		Funcionalidade f2 = new Funcionalidade("consulta", "con", true);
		Funcionalidade f3 = new Funcionalidade( "atualiza", "atu", true);
		Funcionalidade f4 = new Funcionalidade("deleta", "del", true);
		
		funcionalidadeReposytory.saveAll(Arrays.asList(f1,f2,f3,f4));
		
		Perfil p1 = new Perfil("Assistente", "Assistente", true);
		p1.getFuncionalidades().add(f1);
        p1.getFuncionalidades().add(f2);
        p1.getFuncionalidades().add(f3);
        p1.getFuncionalidades().add(f4);
 
		Perfil p2 = new Perfil( "Atendente", "Atendente", true);
		p2.getFuncionalidades().add(f2);
        p2.getFuncionalidades().add(f3);
		
		Perfil p3 = new Perfil( "Supervisor", "Supervisor", true);
		p3.getFuncionalidades().add(f1);
        p3.getFuncionalidades().add(f2);
        p3.getFuncionalidades().add(f3);
		
		Perfil p4 = new Perfil("Coordenador", "Coordenador", true);
		p4.getFuncionalidades().add(f2);
		p4.getFuncionalidades().add(f4);
		
		perfilRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
		
		
		Usuario user1 = new Usuario("Alan Lima", "alima", encode.encode("12345"), "alan.makau@gmail.com",true);
		user1.getPerfil().add(p2);
		user1.getPerfil().add(p3);
		
		Usuario user2 = new Usuario("Fernanda Santos", "fsantos", encode.encode("123321"), "al11an.makau@gmail.com",true);
		user2.getPerfil().add(p1);
		user2.getPerfil().add(p2);
		user2.getPerfil().add(p3);
		
		Usuario user3 = new Usuario("Maria julia", "maju", encode.encode("123654"), "alan.ma11kau@gmail.com",true);
		user3.getPerfil().add(p2);
		user3.getPerfil().add(p3);
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2,user3));
		*/
	}

}
