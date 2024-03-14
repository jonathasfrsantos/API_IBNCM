package com.jonathasfrsaint.API_IBNCM.services.exceptions;

import com.jonathasfrsaint.API_IBNCM.entities.Finality;

public class ThisValueAlreadyExists extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ThisValueAlreadyExists(String msg, Finality finality) {
		super(finality.getDescription() + " Já está cadastrada! ");
	}
	

}
