package com.devcoi.dclog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<StandardError.Campo> campos = new ArrayList<>();

		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) objectError).getField();
			//String mensagem = objectError.getDefaultMessage();
			String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			campos.add(new StandardError.Campo(nome, mensagem));
		}

		StandardError error = new StandardError(status.value(), LocalDateTime.now(),
				"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", campos);
		return handleExceptionInternal(ex, error, headers, status, request);
	}

}
