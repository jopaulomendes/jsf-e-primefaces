package com.jopaulo.id;

import java.math.BigDecimal;

import javax.inject.Inject;

public class RelatorioServices {

	@Inject
	private Pedidos pedidos;
	
	public RelatorioServices(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	
	public RelatorioServices() {
		
	}
	
	public BigDecimal totalPedidosMesAtual() {
		return pedidos.totalPedidosMesAtual();
	}
	
	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
}
