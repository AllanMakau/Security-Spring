package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	
	Conta findByTipoConta(String tipoConta);
}
