package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.security.control.entity.Meio;
import br.com.security.control.repository.MeioRepository;


@Service
public class MeioService {
	
	@Autowired
	private MeioRepository meioRepository;
	
	public List<Meio> obterMeios(){
		List<Meio> meio = meioRepository.findAll();
		return meio;
	}
	
	
	public Meio obterMeioPorId(Long id){
		Meio meio = meioRepository.findById(id).get();
		return meio;
	}
	
	
	public void excluirMeioPorId(Long id){
		meioRepository.deleteById(id);
	}
	
	
	public Meio atualizarMeio(Meio m){
		Meio meio = meioRepository.save(m);
		return meio;
	}
	

	public Meio atualizarMeio(Long id ,Meio meio){
		
		Meio meioAtual =meioRepository.getOne(id);
		BeanUtils.copyProperties(meio, meioAtual, "id");
		Meio retorno = meioRepository.save(meioAtual);
		return retorno;
	}
	
	public Meio cadastrarMeio(Meio m){
		Meio meio = meioRepository.save(m);
		return meio;
	}
	
	public Meio ativarMeio(Long id, Boolean ativo){
		Meio m = meioRepository.findById(id).get();
		m.setAtivo(ativo);
		Meio meio = meioRepository.save(m);
		return meio;
	}

}
