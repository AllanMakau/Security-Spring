package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	 Usuario findByLogin(String login );

	 Usuario findByEmail(String email);

}
