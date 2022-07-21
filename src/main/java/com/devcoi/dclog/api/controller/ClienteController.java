package com.devcoi.dclog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcoi.dclog.domain.model.Cliente;

@RestController
public class ClienteController {

	@PersistenceContext
	private EntityManager em;

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return em.createQuery("from Cliente", Cliente.class).getResultList();
	}

}
