package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.security.control.entity.Conta;
import br.com.security.control.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	
	public List<Conta> obterLista() {	
		List<Conta> contas = contaRepository.findAll();	
		return contas;
	}
	
	
	public Conta obterContaPorId(Long id) {	
		Conta conta = contaRepository.findById(id).get();	
		return conta;
	}
	
	
}
