package br.com.security.control.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.annotations.common.reflection.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.security.control.DTO.EmailDTO;
import br.com.security.control.emailUtil.EmailService;
import br.com.security.control.entity.Conta;
import br.com.security.control.entity.Usuario;
import br.com.security.control.service.ContaService;
import br.com.security.control.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	@RequestMapping(method = RequestMethod.GET )
	public ResponseEntity<?> listaUsuarios(){
		List<Usuario> usuarios = usuarioService.obterUsuarios();
		return ResponseEntity.ok(usuarios);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario user){
		Usuario usuario = usuarioService.cadastrarUsuario(user);
		return ResponseEntity.ok(usuario);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Usuario usuario = usuarioService.obterUsuarioPorId(id);
		return ResponseEntity.ok(usuario);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		 usuarioService.excluirUsuarioPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario user){
		Usuario usuario = usuarioService.atualizarUsuario(id, user); 
		return ResponseEntity.ok(usuario);
	}
	
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String,Object> campos){
		
		Usuario usuarioAtual = usuarioService.obterUsuarioPorId(id);
		merge(campos,usuarioAtual);
	
		return this.atualizar(id, usuarioAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Usuario usuarioDestino) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Usuario usuarioOrigem= objectMapper.convertValue(dadosOrigem, Usuario.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Usuario.class, nomePropriedade);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, usuarioOrigem);
			ReflectionUtils.setField(field, usuarioDestino, novoValor);
		});
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('cad')")
	public ResponseEntity<?> ativarUsuario(@PathVariable Long id, @PathVariable Boolean ativo ){
		Usuario usuario = usuarioService.ativarUsuario(id, ativo); 
		return ResponseEntity.ok(usuario);
	}
	
	
}
