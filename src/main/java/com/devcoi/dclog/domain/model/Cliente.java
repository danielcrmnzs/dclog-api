package com.devcoi.dclog.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String telefone;

}
