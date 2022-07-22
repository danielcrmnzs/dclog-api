package com.devcoi.dclog.api.model;

import com.devcoi.dclog.domain.model.Destinatario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinatarioDTO {

	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;

	public DestinatarioDTO(Destinatario destinatario) {
		this.nome = destinatario.getNome();
		this.logradouro = destinatario.getLogradouro();
		this.numero = destinatario.getNumero();
		this.complemento = destinatario.getComplemento();
		this.bairro = destinatario.getBairro();
	}

}
