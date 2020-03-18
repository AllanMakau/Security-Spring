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
import br.com.security.control.entity.TipoAssunto;
import br.com.security.control.event.RecursoCriadoEvent;
import br.com.security.control.service.TipoAssuntoService;

@RestController
@RequestMapping(value = "/tipo-assunto")
public class TipoAssuntoController {
	
	
	@Autowired
	private TipoAssuntoService tipoAssuntoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaTipoAssuntos(){
		List<TipoAssunto> tipoAssuntos = tipoAssuntoService.obterTipoAssuntos();
		return ResponseEntity.ok(tipoAssuntos);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> cadastrarTipoAssunto( @RequestBody TipoAssunto c, HttpServletResponse response){
		TipoAssunto tipoAssunto = tipoAssuntoService.cadastrarTipoAssunto(c);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoAssunto.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoAssunto);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		TipoAssunto tipoAssunto = tipoAssuntoService.obterTipoAssuntoPorId(id);
		return ResponseEntity.ok(tipoAssunto);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		tipoAssuntoService.excluirTipoAssuntoPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody TipoAssunto c){
		TipoAssunto tipoAssunto = tipoAssuntoService.atualizarTipoAssunto(c); 
		return ResponseEntity.ok(tipoAssunto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarPorId(@PathVariable Long id, @RequestBody TipoAssunto c){
		TipoAssunto tipoAssunto = tipoAssuntoService.atualizarTipoAssunto(c); 
		return ResponseEntity.ok(tipoAssunto);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarTipoAssunto(@PathVariable Long id, @PathVariable Boolean ativo ){
		TipoAssunto tipoAssunto = tipoAssuntoService.ativarTipoAssunto(id, ativo); 
		return ResponseEntity.ok(tipoAssunto);
	}

}
