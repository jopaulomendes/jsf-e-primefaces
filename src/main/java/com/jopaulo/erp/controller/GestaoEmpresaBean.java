package com.jopaulo.erp.controller;

import java.io.Serializable;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jopaulo.erp.model.Empresa;
import com.jopaulo.erp.model.RamoAtividade;
import com.jopaulo.erp.model.TipoEmpresa;
import com.jopaulo.erp.repository.Empresas;
import com.jopaulo.erp.repository.RamoAtividades;
import com.jopaulo.erp.service.CadastroEmpresaService;
import com.jopaulo.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private RamoAtividades ramoAtividades;
	
	@Inject
	private CadastroEmpresaService cadastroEmpresaService;
	
	private List<Empresa> listaEmpresas;
	
	private String termoPesquisar;
	
	private Converter ramoAtividadeConverter;
	
	private Empresa empresa;
	
	public void prepararNovaEpresa() {
		empresa = new Empresa();
	}
	
	public void prepararEdicao() {
		ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
	}
	
	public void salvar() {
		cadastroEmpresaService.salvar(empresa);
		
		atualizarRegistros();
		
		messages.info("Empresa salva com sucesso!");
		
		org.primefaces.context.RequestContext.getCurrentInstance().update(Arrays.asList("frm:empresaDataTable", "frm:messages"));
	}
	
	public void excluir() {
		cadastroEmpresaService.excluir(empresa);
		
		empresa = null;
		
		atualizarRegistros();
		
		messages.info("Empresa excluída com sucesso!");
	}
	
	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisar);
		
		if (listaEmpresas.isEmpty()) {
			messages.info("Consulta não retornou rigistros");
		}
	}
	
	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}
	
	public List<RamoAtividade> completarRamoAtividade(String termo){
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
		
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
		
		return listaRamoAtividades;
	}
	
	private void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todasEmpresas();
		}
	}
	
	private boolean jaHouvePesquisa() {
		return termoPesquisar != null && !"".equals(termoPesquisar);
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
	
	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public boolean isEmpresaSelecionado() {
		return empresa != null && empresa.getId() != null;
	}
}
