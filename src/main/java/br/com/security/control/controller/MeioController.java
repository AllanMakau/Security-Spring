package br.com.security.control.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.security.control.entity.Meio;
import br.com.security.control.event.RecursoCriadoEvent;
import br.com.security.control.service.MeioService;

@RestController
@RequestMapping(value = "/meio")
public class MeioController {
	
	
	@Autowired
	private MeioService meioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaMeios(){
		List<Meio> meios = meioService.obterMeios();
		return ResponseEntity.ok(meios);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> cadastrarMeio( @RequestBody Meio c, HttpServletResponse response){
		Meio meio = meioService.cadastrarMeio(c);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, meio.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(meio);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Meio meio = meioService.obterMeioPorId(id);
		return ResponseEntity.ok(meio);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		meioService.excluirMeioPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody Meio c){
		Meio meio = meioService.atualizarMeio(c); 
		return ResponseEntity.ok(meio);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarPorId(@PathVariable Long id, @RequestBody Meio c){
		Meio meio = meioService.atualizarMeio(c); 
		return ResponseEntity.ok(meio);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarMeio(@PathVariable Long id, @PathVariable Boolean ativo ){
		Meio meio = meioService.ativarMeio(id, ativo); 
		return ResponseEntity.ok(meio);
	}

}
