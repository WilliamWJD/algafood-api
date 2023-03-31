package com.wjd.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjd.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.wjd.algafood.domain.model.Restaurante;
import com.wjd.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteService.listar();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable(name = "restauranteId") final Long restauranteId){
		try {
			return ResponseEntity.ok(restauranteService.buscar(restauranteId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}