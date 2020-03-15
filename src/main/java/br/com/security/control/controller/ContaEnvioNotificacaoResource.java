package br.com.security.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.security.control.entity.Conta;
import br.com.security.control.service.ContaEnvioNotificacaoService;

@RestController
@RequestMapping(value = "/conta-notificacao")
public class ContaEnvioNotificacaoResource {
	
	
	@Autowired
	private ContaEnvioNotificacaoService contaService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listacontas(){
		List<Conta> contas = contaService.obterContas();
		return ResponseEntity.ok(contas);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Conta conta = contaService.obterContaPorId(id);
		return ResponseEntity.ok(conta);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		contaService.excluirContaPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody Conta conta){
		Conta novaconta = contaService.atualizarConta(conta); 
		return ResponseEntity.ok(novaconta);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarPerfil(@PathVariable Long id, @PathVariable Boolean ativo ){
		Conta conta = contaService.ativarConta(id, ativo); 
		return ResponseEntity.ok(conta);
	}

}
