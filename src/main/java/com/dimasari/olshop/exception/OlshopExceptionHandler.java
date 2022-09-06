package com.dimasari.olshop.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dimasari.olshop.constant.ResponseCodeConstant;
import com.dimasari.olshop.dto.BaseResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class OlshopExceptionHandler extends ResponseEntityExceptionHandler {
	private ResponseEntity<Object> handleResponseEntity(String code, String message) {
		var response = new BaseResponse<>(null);
		response.setResponseCode(code);
		response.setResponseMessage(message);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var error = new StringBuilder();
		if (!ex.getAllErrors().isEmpty()) {
			ex.getAllErrors().forEach(data -> {
				if (error.length() > 0) {
					error.append(",");
					error.append(" ");
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

				error.append(sb);
			});
		}
		return handleResponseEntity(ResponseCodeConstant.METHOD_ARGUMENT_NOT_VALID, error.toString());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		return handleResponseEntity(ResponseCodeConstant.GENERAL_ERROR, e.getMessage());
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> dataNotFoundException(DataNotFoundException e) {
		return handleResponseEntity(ResponseCodeConstant.DATA_NOT_FOUND, e.getMessage());
	}
}
