package com.devcoi.dclog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {

	private Integer status;
	private LocalDateTime dataHora;
	private String erro;
	private List<Campo> campos;

	@Getter
	@AllArgsConstructor
	public static class Campo {
		private String nome;
		private String mensagem;
	}

}
