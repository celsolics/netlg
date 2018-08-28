package com.csli.netlg.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.csli.netlg.services.exceptions.ObjectNotFoundException;


/***
 * Personalizar resposta de erros para um json
 * @author celso
 * Manipulador de erros, classe que intercepta os erros
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) /* Anotaçao - que trata exception desse tipo*/
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError sErr = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sErr);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class) /* Anotaçao - que trata exception desse tipo*/
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request){
		StandardError sErr = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sErr);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) /* Anotaçao - que trata exception desse tipo*/
	public ResponseEntity<StandardError> dataIntegrity(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError sErr = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		
		for(FieldError x : e.getBindingResult().getFieldErrors) {
			sErr.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sErr);
	}
}
