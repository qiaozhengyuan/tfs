package com.tfs.q1jwa;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomErrorHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomErrorHandler.class);

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorReply> handleConstraintViolationException(ConstraintViolationException exception) {
		ErrorReply errorReply = new ErrorReply();
		errorReply.setCode("ConstraintViolation");
		errorReply.setMessage(exception.getMessage());
		return ResponseEntity.badRequest().body(errorReply);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorReply> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		ErrorReply errorReply = new ErrorReply();
		errorReply.setCode("InvalidInput");
		errorReply.setMessage(exception.getMessage());
		return ResponseEntity.badRequest().body(errorReply);
	}

	// Default exception handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorReply> handleAllExceptions(Exception ex, WebRequest request) {
		String errorID = UUID.randomUUID().toString();
		logger.error(errorID + ":" + request.toString());
		logger.error(errorID, ex);
		ErrorReply errorReply = new ErrorReply();
		errorReply.setCode("UnexpectedError");
		errorReply.setMessage(errorID + "\n" + ex.getMessage());
		return ResponseEntity.internalServerError().body(errorReply);
	}

	private class ErrorReply {
		private String code;
		private String message;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
