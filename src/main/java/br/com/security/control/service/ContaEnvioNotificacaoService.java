package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.security.control.entity.Conta;
import br.com.security.control.repository.ContaRepository;

@Service
public class ContaEnvioNotificacaoService {
	
	
	@Autowired
	private ContaRepository contaRepository;
	
	
	
	public List<Conta> obterContas(){
		List<Conta> contas = contaRepository.findAll();
		return contas;
	}
	
	public Conta obterContaPorId(Long id){
		Conta conta = contaRepository.findById(id).get();
		return conta;
	}
	
	public void excluirContaPorId(Long id){
		contaRepository.deleteById(id);
	}
	
	
	public Conta atualizarConta(Conta conta){
		Conta novaconta = contaRepository.save(conta);
		return novaconta;
	}
	
	
	public Conta cadastrarConta(Conta conta){
		Conta novaconta = contaRepository.save(conta);
		return novaconta;
	}
	
	public Conta ativarConta(Long id, Boolean ativo){
		Conta conta = contaRepository.findById(id).get();
		conta.setAtivo(ativo);
		Conta novaConta = contaRepository.save(conta);
		return novaConta;
	}
	

}
