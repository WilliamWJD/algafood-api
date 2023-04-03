package com.wjd.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjd.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.wjd.algafood.domain.model.Cidade;
import com.wjd.algafood.domain.service.CidadeService;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

	@Autowired
	private CidadeService CidadeService;

	@GetMapping
	public ResponseEntity<List<Cidade>> listar() {
		return ResponseEntity.ok(CidadeService.listar());
	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<?> buscar(@PathVariable(name = "cidadeId") final Long cidadeId) {
		try {
			return ResponseEntity.ok(CidadeService.buscar(cidadeId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody final Cidade Cidade) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(CidadeService.salvar(Cidade));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?>atualizar(@RequestBody final Cidade Cidade, @PathVariable(name = "cidadeId") final Long cidadeId){
		try {
			return ResponseEntity.ok(CidadeService.atualizar(Cidade, cidadeId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
