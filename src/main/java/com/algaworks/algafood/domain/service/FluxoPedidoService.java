package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Pedido;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
//		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
//			throw new NegocioException(String.format("Status do pedido %d nao pode ser alterado de %s para %s",
//					pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
//		}
//		pedido.setStatus(StatusPedido.CONFIRMADO);
//		pedido.setDataConfirmacao(OffsetDateTime.now());
		pedido.confirmar();
	}

	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.cancelar();
	}

	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.entregar();
	}
}
