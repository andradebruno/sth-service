package br.com.bruno.sth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.bruno.sth.exception.TicketHolderException;
import br.com.bruno.sth.response.ErrorResponse;

/**
 * Controller para lidar com os erros da aplicação.
 *
 * @author Bruno Andrade
 * @email andradedbruno@gmail.com
 */
@RestControllerAdvice
public class ErrorController {

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleInternalServerError(Exception ex, WebRequest req) {
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class,
			IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBadRequest(Exception ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = { TicketHolderException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleTicketHolderFound(Exception ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}

}
