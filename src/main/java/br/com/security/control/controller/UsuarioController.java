package br.com.security.control.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.control.DTO.NovaSenhaDTO;
import br.com.security.control.entity.Usuario;
import br.com.security.control.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	@RequestMapping(method = RequestMethod.GET )
	@PreAuthorize("hasAuthority('del')")
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
	
	
	@PatchMapping(value = "/{id}" )
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String,Object> campos){
		
		Usuario usuarioAtual = usuarioService.obterUsuarioPorId(id);
		usuarioService.merge(campos,usuarioAtual);
	
		return this.atualizar(id, usuarioAtual);
	}


	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('cad')")
	public ResponseEntity<?> ativarUsuario(@PathVariable Long id, @PathVariable Boolean ativo ){
		Usuario usuario = usuarioService.ativarUsuario(id, ativo); 
		return ResponseEntity.ok(usuario);
	}
	
	@RequestMapping(value = "/renovar-senha/{id}")
	public Usuario resetSenha(@PathVariable Long id,@Valid @RequestBody NovaSenhaDTO senha){
		
		if(senha.getSenha1().equals(senha.getSenha2())) {
			new Exception();
		}
		Usuario senhaAtualizada = usuarioService.resetarSenha( id, senha);
		return senhaAtualizada;
	}
	
	
}
