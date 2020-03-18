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

import br.com.security.control.entity.Status;
import br.com.security.control.event.RecursoCriadoEvent;
import br.com.security.control.service.StatusService;

@RestController
@RequestMapping(value = "/status")
public class StatusController {
	
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaStatuss(){
		List<Status> statuss = statusService.obterStatus();
		return ResponseEntity.ok(statuss);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> cadastrarStatus( @RequestBody Status c, HttpServletResponse response){
		Status status = statusService.cadastrarStatus(c);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, status.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(status);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Status status = statusService.obterStatusPorId(id);
		return ResponseEntity.ok(status);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		statusService.excluirStatusPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody Status c){
		Status status = statusService.atualizarStatus(c); 
		return ResponseEntity.ok(status);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarPorId(@PathVariable Long id, @RequestBody Status c){
		Status status = statusService.atualizarStatus(c); 
		return ResponseEntity.ok(status);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarStatus(@PathVariable Long id, @PathVariable Boolean ativo ){
		Status status = statusService.ativarStatus(id, ativo); 
		return ResponseEntity.ok(status);
	}

}
