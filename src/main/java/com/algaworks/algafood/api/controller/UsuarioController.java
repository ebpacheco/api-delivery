package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.UsuarioDTOAssembler;
import com.algaworks.algafood.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.model.UsuarioDTO;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;

	@GetMapping
	public List<UsuarioDTO> listar() {
		return usuarioDTOAssembler.toCollectionDTO(usuarioRepository.findAll());
	}

	@GetMapping(value = "/{usuarioId}")
	public UsuarioDTO buscar(@PathVariable Long usuarioId) {
		return usuarioDTOAssembler.toDTO(cadastroUsuarioService.buscarOuFalhar(usuarioId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioDTO adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
		return usuarioDTOAssembler.toDTO(cadastroUsuarioService.salvar(usuario));
	}

	@PutMapping("/{usuarioId}")
	public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuario);
		return usuarioDTOAssembler.toDTO(cadastroUsuarioService.salvar(usuario));
	}

	@PutMapping("/senha/{usuarioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
		cadastroUsuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
}
