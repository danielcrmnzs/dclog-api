package com.devcoi.dclog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcoi.dclog.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente(1L, "João da Silva", "joao@devcoi.com", "21 999-9999");
		var cliente2 = new Cliente(2L, "Maria Rita", "maria@devcoi.com", "31 988-9999");

		return Arrays.asList(cliente1, cliente2);
	}

}
