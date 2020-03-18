package br.com.security.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.control.entity.Status;


@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

}
