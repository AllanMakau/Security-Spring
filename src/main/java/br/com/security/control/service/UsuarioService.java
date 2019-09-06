package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.security.control.entity.Usuario;
import br.com.security.control.repository.UsuarioRepository;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	public List<Usuario> obterUsuarios(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}
	
	public Usuario obterUsuarioPorId(Long id){
		Usuario usuario = usuarioRepository.findById(id).get();
		return usuario;
	}
	
	public void excluirUsuarioPorId(Long id){
		 usuarioRepository.deleteById(id);
	}
	
	
	public Usuario atualizarUsuario(Usuario user){
		Usuario usuario = usuarioRepository.save(user);
		return usuario;
	}
	
	public Usuario cadastrarUsuario(Usuario user){
		Usuario usuario = usuarioRepository.save(user);
		return usuario;
	}
	
	public Usuario ativarUsuario(Long id, Boolean ativo){
		Usuario user = usuarioRepository.findById(id).get();
		user.setAtivo(ativo);
		Usuario usuario = usuarioRepository.save(user);
		return usuario;
	}
	
	

	
}
