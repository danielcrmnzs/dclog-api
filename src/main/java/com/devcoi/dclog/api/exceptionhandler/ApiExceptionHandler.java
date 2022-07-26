package com.devcoi.dclog.api.exceptionhandler;

import java.time.OffsetDateTime;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devcoi.dclog.domain.exception.EntidadeNaoEncontradaException;
import com.devcoi.dclog.domain.exception.RNException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var validationError = new ValidationError(status.value(), OffsetDateTime.now(),
				"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");

		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) objectError).getField();
			// String mensagem = objectError.getDefaultMessage();
			String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			validationError.addCampo(nome, mensagem);
		}

		return handleExceptionInternal(ex, validationError, headers, status, request);
	}

	@ExceptionHandler(RNException.class)
	public ResponseEntity<Object> handleService(RNException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError error = new StandardError(status.value(), OffsetDateTime.now(), ex.getMessage());

		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError error = new StandardError(status.value(), OffsetDateTime.now(), ex.getMessage());

		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

	}

}
