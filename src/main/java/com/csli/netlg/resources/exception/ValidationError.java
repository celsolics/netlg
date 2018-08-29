package com.csli.netlg.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	//Video 38
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String messagem) {
		erros.add(new FieldMessage(fieldName, messagem));
	}

}