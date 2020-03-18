package br.com.security.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.security.control.entity.TipoAssunto;
import br.com.security.control.repository.TipoAssuntoRepository;

@Service
public class TipoAssuntoService {
	
	@Autowired
	private TipoAssuntoRepository tipoAssuntoRepository;
	
	public List<TipoAssunto> obterTipoAssuntos(){
		List<TipoAssunto> tipoAssunto = tipoAssuntoRepository.findAll();
		return tipoAssunto;
	}
	
	
	public TipoAssunto obterTipoAssuntoPorId(Long id){
		TipoAssunto tipoAssunto = tipoAssuntoRepository.findById(id).get();
		return tipoAssunto;
	}
	
	
	public void excluirTipoAssuntoPorId(Long id){
		tipoAssuntoRepository.deleteById(id);
	}
	
	
	public TipoAssunto atualizarTipoAssunto(TipoAssunto ta){
		TipoAssunto tipoAssunto = tipoAssuntoRepository.save(ta);
		return tipoAssunto;
	}
	

	public TipoAssunto atualizarTipoAssunto(Long id ,TipoAssunto tipoAssunto){
		
		TipoAssunto tipoAssuntoAtual =tipoAssuntoRepository.getOne(id);
		BeanUtils.copyProperties(tipoAssunto, tipoAssuntoAtual, "id");
		TipoAssunto retorno = tipoAssuntoRepository.save(tipoAssuntoAtual);
		return retorno;
	}
	
	public TipoAssunto cadastrarTipoAssunto(TipoAssunto ta){
		TipoAssunto tipoAssunto = tipoAssuntoRepository.save(ta);
		return tipoAssunto;
	}
	
	public TipoAssunto ativarTipoAssunto(Long id, Boolean ativo){
		TipoAssunto ta = tipoAssuntoRepository.findById(id).get();
		ta.setAtivo(ativo);
		TipoAssunto tipoAssunto = tipoAssuntoRepository.save(ta);
		return tipoAssunto;
	}

}
