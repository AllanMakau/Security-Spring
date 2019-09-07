package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.security.control.entity.Funcionalidade;
import br.com.security.control.repository.FuncionalidadeRepository;

@Service
public class FuncionalidadeService {

	@Autowired
	private FuncionalidadeRepository funcionalidadeRepository;
	

	public List<Funcionalidade> obterfuncionalidades(){
		List<Funcionalidade> funcionalidades = funcionalidadeRepository.findAll();
		return funcionalidades;
	}
	
	public Funcionalidade obterFuncionalidadePorId(Long id){
		Funcionalidade funcionalidade = funcionalidadeRepository.findById(id).get();
		return funcionalidade;
	}
	
	
	public void excluirFuncionalidadePorId(Long id){
		funcionalidadeRepository.deleteById(id);
	}
	
	
	public Funcionalidade atualizarFuncionalidade(Funcionalidade f){
		Funcionalidade funcionalidade = funcionalidadeRepository.save(f);
		return funcionalidade;
	}
	
	
	public Funcionalidade cadastrarFuncionalidade(Funcionalidade f){
		Funcionalidade funcionalidade = funcionalidadeRepository.save(f);
		return funcionalidade;
	}
	
	
	public Funcionalidade ativarFuncionalidade(Long id, Boolean ativo){
		Funcionalidade f = funcionalidadeRepository.findById(id).get();
		f.setAtivo(ativo);
		Funcionalidade funcionalidade = funcionalidadeRepository.save(f);
		return funcionalidade;
	}
}
