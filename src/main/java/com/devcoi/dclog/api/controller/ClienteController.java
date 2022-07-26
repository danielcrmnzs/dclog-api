package com.devcoi.dclog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devcoi.dclog.domain.model.Cliente;
import com.devcoi.dclog.domain.repository.ClienteRepository;
import com.devcoi.dclog.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				// .map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

		/*
		 * Optional<Cliente> optional = clienteRepository.findById(clienteId);
		 * 
		 * 
		 * if (optional.isPresent()) { return ResponseEntity.ok(optional.get()); } else
		 * { return ResponseEntity.notFound().build(); }
		 */
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		} else {
			cliente.setId(clienteId);
			cliente = clienteService.salvar(cliente);

			return ResponseEntity.ok(cliente);
		}
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> apagar(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		} else {
			clienteService.excluir(clienteId);
			return ResponseEntity.noContent().build();
		}
	}

}
