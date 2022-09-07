package com.dimasari.olshop.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dimasari.olshop.constant.ResponseCodeConstant;
import com.dimasari.olshop.dto.BaseResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class OlshopExceptionHandler extends ResponseEntityExceptionHandler {
	private ResponseEntity<Object> handleResponseEntity(String code, String message) {
		var response = new BaseResponse<>(null);
		response.setResponseCode(code);
		response.setResponseMessage(message);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var message = new StringBuilder();
		if (!e.getAllErrors().isEmpty()) {
			e.getAllErrors().forEach(data -> {
				if (message.length() > 0) {
					message.append(",");
					message.append(" ");
				}
				var sb = new StringBuilder();
				var fields = data.getCodes()[1];
				var fieldSplit = fields.split("\\.");
				var field = "";
				if (fieldSplit.length > 0) {
					field = fieldSplit[fieldSplit.length - 1];
				}
				sb.append(field);
				sb.append(" ");
				sb.append(data.getDefaultMessage());

				message.append(sb);
			});
		}
		return handleResponseEntity(ResponseCodeConstant.METHOD_ARGUMENT_NOT_VALID, message.toString());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException e) {
		return handleResponseEntity(ResponseCodeConstant.ENTITY_NOT_FOUND, e.getMessage());
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable cause = e.getCause();
		var message = "";
		if (cause instanceof JsonParseException) {
			JsonParseException jpe = (JsonParseException) cause;
			message = jpe.getOriginalMessage();
		} else if (cause instanceof JsonMappingException) {
			JsonMappingException exception = (JsonMappingException) cause;
			String invalidField = exception.getPath().get(0).getFieldName();
			message = invalidField + " " + exception.getOriginalMessage();
		}
		return handleResponseEntity(ResponseCodeConstant.HTTP_MESSAGE_NOT_READABLE, message);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		return handleResponseEntity(ResponseCodeConstant.GENERAL_ERROR, e.getMessage());
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> dataNotFoundException(DataNotFoundException e) {
		return handleResponseEntity(ResponseCodeConstant.DATA_NOT_FOUND, e.getMessage());
	}
	
	@ExceptionHandler(UserUnregisterException.class)
	public ResponseEntity<Object> userUnregisterException(UserUnregisterException e) {
		return handleResponseEntity(ResponseCodeConstant.USER_UNREGISTER, e.getMessage());
	}
}
