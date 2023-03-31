package com.wjd.algafood.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjd.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.wjd.algafood.domain.model.Restaurante;
import com.wjd.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listar(){
		return restauranteRepository.listar();
	}
	
	public Restaurante buscar(final long restauranteId) {
		Restaurante restaurante = restauranteRepository.buscar(restauranteId);
		
		if(Objects.isNull(restaurante)) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de restaurante com o código %d", restauranteId));
		}
		
		return restaurante;
	}
}
