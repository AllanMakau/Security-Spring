package br.com.security.control.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.security.control.DTO.NovaSenhaDTO;
import br.com.security.control.entity.Usuario;
import br.com.security.control.repository.UsuarioRepository;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	
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
	
	public void excluir(Usuario user ) {
		 usuarioRepository.delete(user);
	}
	
	
	public Usuario atualizarUsuario(Long id, Usuario usuario){
		
		Usuario usuarioAtual = this.obterUsuarioPorId(id);
		if(usuarioAtual != null) {
			BeanUtils.copyProperties(usuario, usuarioAtual,"id");
			usuarioRepository.save(usuarioAtual);
			return usuarioAtual;
		}
		return null;
	}
	
	public Usuario cadastrarUsuario(Usuario user){
		BCryptPasswordEncoder en = new BCryptPasswordEncoder();
		user.setSenha(en.encode(user.getSenha()));
		Usuario usuario = usuarioRepository.save(user);
		return usuario;
	}
	
	public Usuario ativarUsuario(Long id, Boolean ativo){
		Usuario user = usuarioRepository.findById(id).get();
		user.setAtivo(ativo);
		Usuario usuario = usuarioRepository.save(user);
		return usuario;
	}
	public Usuario resetarSenha( Long id,NovaSenhaDTO  senha) {
		
		Usuario user = usuarioRepository.findById(id).get();
		user.setSenha(bcrypt.encode(senha.getSenha1()));
		Usuario usuario = usuarioRepository.save(user);
		
		return usuario;
		
	}
	
	public void merge(Map<String, Object> dadosOrigem, Usuario usuarioDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Usuario usuarioOrigem= objectMapper.convertValue(dadosOrigem, Usuario.class);
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Usuario.class, nomePropriedade);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, usuarioOrigem);
			ReflectionUtils.setField(field, usuarioDestino, novoValor);
		});
	}
	
	

	
}
