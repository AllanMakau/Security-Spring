package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.Meio;

@Repository
public interface MeioRepository extends JpaRepository<Meio, Long>{

}
