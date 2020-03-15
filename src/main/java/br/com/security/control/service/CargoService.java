package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.security.control.entity.Cargo;
import br.com.security.control.entity.Usuario;
import br.com.security.control.repository.CargoRepository;


@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	
	public List<Cargo> obterCargos(){
		List<Cargo> cargos = cargoRepository.findAll();
		return cargos;
	}
	
	
	public Cargo obterCargoPorId(Long id){
		Cargo cargo = cargoRepository.findById(id).get();
		return cargo;
	}
	
	
	public void excluirCargoPorId(Long id){
		cargoRepository.deleteById(id);
	}
	
	
	public Cargo atualizarCargo(Cargo user){
		Cargo cargo = cargoRepository.save(user);
		return cargo;
	}
	

	public Cargo atualizarCargo(Long id ,Cargo cargo){
		
		Cargo cargoAtual =cargoRepository.getOne(id);
		BeanUtils.copyProperties(cargo, cargoAtual, "id");
		Cargo retorno = cargoRepository.save(cargoAtual);
		return retorno;
	}
	
	public Cargo cadastrarCargo(Cargo c){
		Cargo cargo = cargoRepository.save(c);
		return cargo;
	}
	
	public Cargo ativarCargo(Long id, Boolean ativo){
		Cargo c = cargoRepository.findById(id).get();
		c.setAtivo(ativo);
		Cargo cargo = cargoRepository.save(c);
		return cargo;
	}
}
