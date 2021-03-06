package com.srm.clientapi.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerSpring extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	// payload invalido
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String msgUser = messageSource.getMessage("msg.invalida", null, LocaleContextHolder.getLocale());
		String msgDeveloper = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> listErros = Arrays.asList(new Error(msgUser, msgDeveloper));
		return handleExceptionInternal(ex, listErros, headers, HttpStatus.BAD_REQUEST, request);
	}

	// qualquer bad request
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> listErros = createListError(ex.getBindingResult());

		return handleExceptionInternal(ex, listErros, headers, HttpStatus.BAD_REQUEST, request);
	}

	// informacao nao encontrada na base, byfindid
	@ExceptionHandler({ NoSuchElementException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(NoSuchElementException ex, WebRequest request) {
		String msgUser = messageSource.getMessage("msg.not_found_resource", null, LocaleContextHolder.getLocale());
		String msgDeveloper = ExceptionUtils.getRootCauseMessage(ex);
		log.error(msgDeveloper);
		return handleExceptionInternal(ex, Arrays.asList(msgUser, msgDeveloper), new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> otherErrors(Exception ex) {
		String msgUser = messageSource.getMessage("msg.default_error", null, LocaleContextHolder.getLocale());
		String msgDeveloper = ExceptionUtils.getRootCauseMessage(ex);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(msgUser, msgDeveloper));
	}

	private List<Error> createListError(BindingResult results) {
		List<Error> listErros = new ArrayList<>();
		for (FieldError fieldErro : results.getFieldErrors()) {
			String msgUser = messageSource.getMessage(fieldErro, LocaleContextHolder.getLocale());
			String msgDeveloper = fieldErro.toString();
			listErros.add(new Error(msgUser, msgDeveloper));
		}

		return listErros;
	}

	public static class Error {

		public Error(final String msgUser, final String msgDeveloper) {
			this.msgUser = msgUser;
			this.msgDeveloper = msgDeveloper;
		}

		private String msgUser;
		private String msgDeveloper;

		public String getMsgUser() {
			return msgUser;
		}

		public void setMsgUser(String msgUser) {
			this.msgUser = msgUser;
		}

		public String getMsgDeveloper() {
			return msgDeveloper;
		}

		public void setMsgDeveloper(String msgDeveloper) {
			this.msgDeveloper = msgDeveloper;
		}

	}
}
