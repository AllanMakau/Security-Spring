package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.Perfil;


@Repository
public interface PerfilRespository extends JpaRepository<Perfil, Long> {

}
