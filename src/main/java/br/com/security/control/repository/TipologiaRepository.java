package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.Tipologia;


@Repository
public interface TipologiaRepository extends JpaRepository<Tipologia, Long>{

}
