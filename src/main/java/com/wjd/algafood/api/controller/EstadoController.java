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
import com.wjd.algafood.domain.model.Estado;
import com.wjd.algafood.domain.service.EstadoService;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public ResponseEntity<List<Estado>> listar() {
		return ResponseEntity.ok(estadoService.listar());
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<?> buscar(@PathVariable(name = "estadoId") final Long estadoId) {
		try {
			return ResponseEntity.ok(estadoService.buscar(estadoId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody final Estado estado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.salvar(estado));
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<?>atualizar(@RequestBody final Estado estado, @PathVariable(name = "estadoId") final Long estadoId){
		try {
			return ResponseEntity.ok(estadoService.atualizar(estado, estadoId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
