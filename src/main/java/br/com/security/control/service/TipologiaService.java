package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.security.control.entity.Tipologia;
import br.com.security.control.repository.TipologiaRepository;

@Service
public class TipologiaService {
	
	@Autowired
	private TipologiaRepository tipologiaRepository;
	
	public List<Tipologia> obterTipologias(){
		List<Tipologia> tipologia = tipologiaRepository.findAll();
		return tipologia;
	}
	
	
	public Tipologia obterTipologiaPorId(Long id){
		Tipologia tipologia = tipologiaRepository.findById(id).get();
		return tipologia;
	}
	
	
	public void excluirTipologiaPorId(Long id){
		tipologiaRepository.deleteById(id);
	}
	
	
	public Tipologia atualizarTipologia(Tipologia s){
		Tipologia tipologia = tipologiaRepository.save(s);
		return tipologia;
	}
	

	public Tipologia atualizarTipologia(Long id ,Tipologia tipologia){
		
		Tipologia tipologiaAtual =tipologiaRepository.getOne(id);
		BeanUtils.copyProperties(tipologia, tipologiaAtual, "id");
		Tipologia retorno = tipologiaRepository.save(tipologiaAtual);
		return retorno;
	}
	
	public Tipologia cadastrarTipologia(Tipologia c){
		Tipologia tipologia = tipologiaRepository.save(c);
		return tipologia;
	}
	
	public Tipologia ativarTipologia(Long id, Boolean ativo){
		Tipologia t = tipologiaRepository.findById(id).get();
		t.setAtivo(ativo);
		Tipologia tipologia = tipologiaRepository.save(t);
		return tipologia;
	}


}
