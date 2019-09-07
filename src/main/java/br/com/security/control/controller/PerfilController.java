package br.com.security.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.control.entity.Perfil;
import br.com.security.control.service.PerfilService;

@RestController
@RequestMapping(value = "/perfil")
public class PerfilController {

	
	@Autowired
	private PerfilService perfilService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaPerfil(){
		List<Perfil> perfis = perfilService.obterPerfis();
		return ResponseEntity.ok(perfis);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> cadastrarPerfil( @RequestBody Perfil c){
		Perfil perfil = perfilService.cadastrarPerfil(c);
		return ResponseEntity.ok(perfil);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Perfil perfil = perfilService.obterCargoPorId(id);
		return ResponseEntity.ok(perfil);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		perfilService.excluirCargoPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody Perfil p){
		Perfil perfil = perfilService.atualizarPerfil(p); 
		return ResponseEntity.ok(perfil);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarPerfil(@PathVariable Long id, @PathVariable Boolean ativo ){
		Perfil perfil = perfilService.ativarPerfil(id, ativo); 
		return ResponseEntity.ok(perfil);
	}
}
