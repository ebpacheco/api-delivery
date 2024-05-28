package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoDTOAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoDTOAssembler;
import com.algaworks.algafood.api.model.PedidoDTO;
import com.algaworks.algafood.api.model.PedidoResumoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	CadastroPedidoService cadastroPedidoService;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	PedidoDTOAssembler pedidoDTOAssembler;

	@Autowired
	PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

	@GetMapping
	public List<PedidoResumoDTO> listar() {
		List<Pedido> todosPedidos = pedidoRepository.findAll();
		return pedidoResumoDTOAssembler.toCollectionDTO(todosPedidos);
	}

	@GetMapping("/{pedidoId}")
	public PedidoDTO buscar(@PathVariable Long pedidoId) {
		return pedidoDTOAssembler.toDTO(cadastroPedidoService.buscarOuFalhar(pedidoId));
	}
}
