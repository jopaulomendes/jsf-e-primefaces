package com.jopaulo.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.jopaulo.erp.model.Empresa;
import com.jopaulo.erp.model.TipoEmpresa;

@Named
@ViewScoped
public class GestaoEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa = new Empresa();
	
	public void salvar() {
		System.out.println("Razão social: " + empresa.getRazaoSocial()
				+ " - Nome Fantasia: " + empresa.getNomeFantasia()
				+ " - Tipo: " + empresa.getTipoEmpresa());
	}
	
	public String ajuda() {
		return "AjudaGestaoEmpresa?faces-redirect=true";
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public TipoEmpresa[] getTipoEmpresas() {
		return TipoEmpresa.values();
	}
}
