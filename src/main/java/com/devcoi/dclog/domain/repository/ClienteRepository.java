package com.devcoi.dclog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcoi.dclog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByNome(String nome);

	public List<Cliente> findByNomeContaining(String nome);

	public Optional<Cliente> findByEmail(String email);

}
