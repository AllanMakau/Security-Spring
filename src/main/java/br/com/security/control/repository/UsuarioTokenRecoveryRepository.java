package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.Usuario;
import br.com.security.control.entity.UsuarioTokenRecovery;


@Repository
public interface UsuarioTokenRecoveryRepository extends JpaRepository<UsuarioTokenRecovery, Long> {

	
	UsuarioTokenRecovery findByUsuario(Usuario usuario);
	
	UsuarioTokenRecovery findByToken(String token);
	
}
