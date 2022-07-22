package com.devcoi.dclog.api.exceptionhandler;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError extends StandardError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Campo> campos = new ArrayList<>();
	
	public void addCampo(String nome, String mensagem) {
		campos.add(new Campo(nome, mensagem));
	}

	public ValidationError(Integer status, OffsetDateTime dataHora, String erro) {
		super(status, dataHora, erro);
	}

	@Getter
	@AllArgsConstructor
	private static class Campo {
		private String nome;
		private String mensagem;
	}
}
