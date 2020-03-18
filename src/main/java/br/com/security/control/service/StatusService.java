package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.security.control.entity.Status;
import br.com.security.control.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> obterStatus(){
		List<Status> status = statusRepository.findAll();
		return status;
	}
	
	
	public Status obterStatusPorId(Long id){
		Status status = statusRepository.findById(id).get();
		return status;
	}
	
	
	public void excluirStatusPorId(Long id){
		statusRepository.deleteById(id);
	}
	
	
	public Status atualizarStatus(Status s){
		Status status = statusRepository.save(s);
		return status;
	}
	

	public Status atualizarStatus(Long id ,Status status){
		
		Status statusAtual =statusRepository.getOne(id);
		BeanUtils.copyProperties(status, statusAtual, "id");
		Status retorno = statusRepository.save(statusAtual);
		return retorno;
	}
	
	public Status cadastrarStatus(Status c){
		Status status = statusRepository.save(c);
		return status;
	}
	
	public Status ativarStatus(Long id, Boolean ativo){
		Status s = statusRepository.findById(id).get();
		s.setAtivo(ativo);
		Status status = statusRepository.save(s);
		return status;
	}

}
