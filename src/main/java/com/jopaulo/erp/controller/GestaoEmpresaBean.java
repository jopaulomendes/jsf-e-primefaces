package com.jopaulo.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class GestaoEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Integer NUMERO = 0;
	
	public GestaoEmpresaBean() {
		NUMERO++;
	}
	
	public Integer getNumero() {
		return NUMERO;
	}
}
