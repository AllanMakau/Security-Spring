package br.com.security.control.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.control.entity.Cargo;
import br.com.security.control.service.CargoService;


@RestController
@RequestMapping(value = "/cargo")
public class CargoController {

	
	@Autowired
	private CargoService cargoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listaCargos(){
		List<Cargo> cargos = cargoService.obterCargos();
		return ResponseEntity.ok(cargos);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> cadastrarCargo( @RequestBody Cargo c){
		Cargo cargo = cargoService.cadastrarCargo(c);
		return ResponseEntity.ok(cargo);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable Long id){
		Cargo cargo = cargoService.obterCargoPorId(id);
		return ResponseEntity.ok(cargo);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> excluirPorId(@PathVariable Long id){
		cargoService.excluirCargoPorId(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar( @RequestBody Cargo c){
		Cargo cargo = cargoService.atualizarCargo(c); 
		return ResponseEntity.ok(cargo);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarUsuario(@PathVariable Long id, @PathVariable Boolean ativo ){
		Cargo cargo = cargoService.ativarCargo(id, ativo); 
		return ResponseEntity.ok(cargo);
	}
}
