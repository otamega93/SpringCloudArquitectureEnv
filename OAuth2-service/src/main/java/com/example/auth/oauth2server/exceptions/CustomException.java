//package com.example.auth.oauth2server.exceptions;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
//
//import org.h2.jdbc.JdbcSQLException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
////@ControllerAdvice
//public class CustomException {
//
//	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	  @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
//	      HttpRequestMethodNotSupportedException.class, ConstraintViolationException.class, JdbcSQLException.class,
//	      DataIntegrityViolationException.class})
//	  public ResponseEntity<Object> badRequest(HttpServletRequest req, Exception exception) {
//		
//		  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//	  }
//}
