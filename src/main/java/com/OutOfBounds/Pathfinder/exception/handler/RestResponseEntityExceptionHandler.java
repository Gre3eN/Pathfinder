package com.OutOfBounds.Pathfinder.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.OutOfBounds.Pathfinder.exception.EntityNotFoundException;
import com.OutOfBounds.Pathfinder.exception.UsernameNotUniqueException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ EntityNotFoundException.class, UsernameNotFoundException.class })
	protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex,
				ex.getMessage(),
				new HttpHeaders(),
				HttpStatus.NOT_FOUND,
				request);
	}

	@ExceptionHandler(UsernameNotUniqueException.class)
	protected ResponseEntity<Object> handleUsernameNotUniqueException(Exception ex,
			WebRequest request) {
		return handleExceptionInternal(ex,
				ex.getMessage(),
				new HttpHeaders(),
				HttpStatus.CONFLICT,
				request);
	}

	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex,
				ex.getMessage(),
				new HttpHeaders(),
				HttpStatus.FORBIDDEN,
				request);
	}
}
