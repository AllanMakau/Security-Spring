package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.TipoAssunto;

@Repository
public interface TipoAssuntoRepository extends JpaRepository<TipoAssunto, Long>{

}
