package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

	@Autowired
	private EnvioEmailService emailService;

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.confirmar();
//		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
//			throw new NegocioException(String.format("Status do pedido %d nao pode ser alterado de %s para %s",
//					pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
//		}
//		pedido.setStatus(StatusPedido.CONFIRMADO);
//		pedido.setDataConfirmacao(OffsetDateTime.now());

		var mensagem = Mensagem.builder().assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
//				.corpo("O pedido de codigo <strong>" + pedido.getCodigo() + "</strong> foi confirmado!")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido).destinatario(pedido.getCliente().getEmail())
				.build();

		emailService.enviar(mensagem);

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
