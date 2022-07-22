package com.devcoi.dclog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devcoi.dclog.api.model.EntregaDTO;
import com.devcoi.dclog.api.model.input.EntregaInputDTO;
import com.devcoi.dclog.assembler.EntregaAssembler;
import com.devcoi.dclog.domain.model.Entrega;
import com.devcoi.dclog.domain.repository.EntregaRepository;
import com.devcoi.dclog.domain.service.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private EntregaService entregaService;

	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInputDTO entregaInputDTO) {
		//return new EntregaDTO(entregaService.solicitarEntrega(entrega));
		Entrega entrega = entregaAssembler.toEntity(entregaInputDTO);
		return entregaAssembler.toDTO(entregaService.solicitarEntrega(entrega));
	}

	@GetMapping
	public List<EntregaDTO> listar() {
//		return entregaRepository
//				.findAll()
//				.stream()
//				.map(entrega -> new EntregaDTO(entrega))
//				.collect(Collectors.toList());
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
		
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
//		return entregaRepository.findById(entregaId).map(entrega -> {
//			EntregaDTO entregaDTO = new EntregaDTO(entrega);
//
//			return ResponseEntity.ok(entregaDTO);
//		}).orElse(ResponseEntity.notFound().build());
		
		return entregaRepository
				.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toDTO(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}

}
