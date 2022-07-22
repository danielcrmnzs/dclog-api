package com.devcoi.dclog.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoi.dclog.domain.model.Cliente;
import com.devcoi.dclog.domain.model.Entrega;
import com.devcoi.dclog.domain.model.StatusEntrega;
import com.devcoi.dclog.domain.repository.EntregaRepository;

@Service
public class EntregaService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EntregaRepository entregaRepository;

	@Transactional
	public Entrega solicitarEntrega(Entrega entrega) {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());

		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());

		return entregaRepository.save(entrega);
	}

}
