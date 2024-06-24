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
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
//		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
//			throw new NegocioException(String.format("Status do pedido %d nao pode ser alterado de %s para %s",
//					pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
//		}
//		pedido.setStatus(StatusPedido.CONFIRMADO);
//		pedido.setDataConfirmacao(OffsetDateTime.now());
		pedido.confirmar();
	}

	@Transactional
	public void cancelar(Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		pedido.cancelar();
	}

	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		pedido.entregar();
	}
}
