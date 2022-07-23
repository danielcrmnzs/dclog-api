package com.devcoi.dclog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devcoi.dclog.api.model.OcorrenciaDTO;
import com.devcoi.dclog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper modelMapper;

	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}

	public List<OcorrenciaDTO> toCollectionModel(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

}
