package com.devcoi.dclog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devcoi.dclog.api.model.EntregaDTO;
import com.devcoi.dclog.api.model.input.EntregaInputDTO;
import com.devcoi.dclog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor		// outra forma de injetar dependencia no Spring, ou utilizar o @Autowired
public class EntregaAssembler {

	private ModelMapper modelMapper;
	
	public EntregaDTO toDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}
	
	public List<EntregaDTO> toCollectionModel(List<Entrega> entregas) {
		return entregas.stream()
				//.map(entrega -> this.toDTO(entrega))    // Faz a mesma coisa que a linha abaixo
				.map(this::toDTO)							// Faz a mesma coisa que a linha acima
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInputDTO input) {
		return modelMapper.map(input, Entrega.class);
	}
	
	
}
