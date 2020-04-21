package com.jopaulo.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jopaulo.erp.model.Empresa;
import com.jopaulo.erp.model.TipoEmpresa;
import com.jopaulo.erp.repository.Empresas;
import com.jopaulo.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private FacesMessages messages;
	
	private List<Empresa> listaEmpresas;
	
	private String termoPesquisar;
	
	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisar);
		
		if (listaEmpresas.isEmpty()) {
			messages.info("Consulta não retornou rigistros");
		}
	}
	
	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public String getTermoPesquisar() {
		return termoPesquisar;
	}

	public void setTermoPesquisar(String termoPesquisar) {
		this.termoPesquisar = termoPesquisar;
	}
	
	public TipoEmpresa[] getTippEmpresa() {
		return TipoEmpresa.values();
	}
}
