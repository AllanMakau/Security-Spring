package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.security.control.entity.Perfil;
import br.com.security.control.repository.PerfilRespository;

@Service
public class PerfilService {


	@Autowired
	private PerfilRespository perfilRepository;
	
	public List<Perfil> obterPerfis(){
		List<Perfil> perfis = perfilRepository.findAll();
		return perfis;
	}
	
	
	public Perfil obterCargoPorId(Long id){
		Perfil perfil = perfilRepository.findById(id).get();
		return perfil;
	}
	
	
	public void excluirCargoPorId(Long id){
		perfilRepository.deleteById(id);
	}
	
	
	public Perfil atualizarPerfil(Perfil p){
		Perfil perfil = perfilRepository.save(p);
		return perfil;
	}
	
	
	public Perfil cadastrarPerfil(Perfil p){
		Perfil perfil = perfilRepository.save(p);
		return perfil;
	}
	
	
	public Perfil ativarPerfil(Long id, Boolean ativo){
		Perfil p = perfilRepository.findById(id).get();
		p.setAtivo(ativo);
		Perfil perfil = perfilRepository.save(p);
		return perfil;
	}
}
