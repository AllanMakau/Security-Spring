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

import br.com.security.control.entity.Tipologia;
import br.com.security.control.event.RecursoCriadoEvent;
import br.com.security.control.service.TipologiaService;

@RestController
@RequestMapping(value = "/tipologia")
public class TipologiaController {
	
	
	@Autowired
	private TipologiaService tipologiaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaTipologias(){
		List<Tipologia> tipologias = tipologiaService.obterTipologias();
		return ResponseEntity.ok(tipologias);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> cadastrarTipologia( @RequestBody Tipologia c, HttpServletResponse response){
		Tipologia tipologia = tipologiaService.cadastrarTipologia(c);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipologia.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(tipologia);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Tipologia tipologia = tipologiaService.obterTipologiaPorId(id);
		return ResponseEntity.ok(tipologia);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		tipologiaService.excluirTipologiaPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody Tipologia c){
		Tipologia tipologia = tipologiaService.atualizarTipologia(c); 
		return ResponseEntity.ok(tipologia);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarPorId(@PathVariable Long id, @RequestBody Tipologia c){
		Tipologia tipologia = tipologiaService.atualizarTipologia(c); 
		return ResponseEntity.ok(tipologia);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarTipologia(@PathVariable Long id, @PathVariable Boolean ativo ){
		Tipologia tipologia = tipologiaService.ativarTipologia(id, ativo); 
		return ResponseEntity.ok(tipologia);
	}

}
