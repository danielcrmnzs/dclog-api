package com.devcoi.dclog.api.exceptionhandler;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private OffsetDateTime dataHora;
	private String erro;

}
