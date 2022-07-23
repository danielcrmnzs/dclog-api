package com.devcoi.dclog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoi.dclog.domain.exception.RNException;
import com.devcoi.dclog.domain.model.Cliente;
import com.devcoi.dclog.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new RNException("Cliente não encontrado"));

	}

	@Transactional
	public Cliente salvar(Cliente cliente) {

		// anyMatch => Verifica se o novo cliente já existe.
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteEncontrado -> !clienteEncontrado.equals(cliente));

		if (emailEmUso) {
			throw new RNException("Já existe cliente cadastrado com este email.");
		}

		return clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
