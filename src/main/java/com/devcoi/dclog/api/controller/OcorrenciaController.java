package com.devcoi.dclog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devcoi.dclog.api.assembler.OcorrenciaAssembler;
import com.devcoi.dclog.api.model.OcorrenciaDTO;
import com.devcoi.dclog.api.model.input.OcorrenciaInputDTO;
import com.devcoi.dclog.domain.model.Entrega;
import com.devcoi.dclog.domain.model.Ocorrencia;
import com.devcoi.dclog.domain.service.EntregaService;
import com.devcoi.dclog.domain.service.OcorrenciaService;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	@Autowired
	private OcorrenciaService ocorrenciaService;

	@Autowired
	private EntregaService entregaService;

	@Autowired
	private OcorrenciaAssembler ocorrenciaAssembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInputDTO ocorrenciaInputDTO) {
		Ocorrencia ocorrencia = ocorrenciaService.registrar(entregaId, ocorrenciaInputDTO.getDescricao());
		return ocorrenciaAssembler.toDTO(ocorrencia);
	}

	@GetMapping
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
		Entrega entrega = entregaService.buscar(entregaId);

		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}

}
