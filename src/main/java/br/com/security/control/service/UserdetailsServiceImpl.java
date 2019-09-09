package br.com.security.control.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.security.control.entity.Funcionalidade;
import br.com.security.control.entity.Perfil;
import br.com.security.control.entity.Usuario;
import br.com.security.control.repository.UsuarioRepository;
import br.com.security.control.security.UserSS;


@Service
public class UserdetailsServiceImpl implements UserDetailsService{

	
	
	@Autowired
    private UsuarioRepository repo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario cli = repo.findByLogin(login);
		if (cli == null) {
			throw new UsernameNotFoundException(login);
		}
		return new UserSS(cli.getId(), cli.getLogin(), cli.getSenha(), this.getAuthorities(cli.getPerfil()));
	}
	
	
	public Collection<GrantedAuthority> getAuthorities(Set<Perfil> perfis){
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Perfil p : perfis) {
			for(Funcionalidade f :  p.getFuncionalidades()) {
				authorities.add(new SimpleGrantedAuthority(f.getChave()));
			}
		}
		
		return authorities;
	}

}
