package br.com.security.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.security.control.entity.Funcionalidade;
import br.com.security.control.service.FuncionalidadeService;

@RestController
@RequestMapping(value = "/funcionalidade")
public class FuncionalidadeController {
	
	
	
	@Autowired
	FuncionalidadeService funcionalidadeService;
	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaFuncionalidade(){
		List<Funcionalidade> funcionalidades = funcionalidadeService.obterfuncionalidades();
		return ResponseEntity.ok(funcionalidades);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> cadastrarFuncionalidade( @RequestBody Funcionalidade f){
		Funcionalidade funcionalidade = funcionalidadeService.cadastrarFuncionalidade(f);
		return ResponseEntity.ok(funcionalidade);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Funcionalidade funcionalidade = funcionalidadeService.obterFuncionalidadePorId(id);
		return ResponseEntity.ok(funcionalidade);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		funcionalidadeService.excluirFuncionalidadePorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody Funcionalidade f){
		Funcionalidade funcionalidade = funcionalidadeService.atualizarFuncionalidade(f); 
		return ResponseEntity.ok(funcionalidade);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarFuncionalidade(@PathVariable Long id, @PathVariable Boolean ativo ){
		Funcionalidade funcionalidade = funcionalidadeService.ativarFuncionalidade(id, ativo); 
		return ResponseEntity.ok(funcionalidade);
	}

}
