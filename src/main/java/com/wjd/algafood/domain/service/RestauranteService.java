package com.wjd.algafood.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjd.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.wjd.algafood.domain.model.Cozinha;
import com.wjd.algafood.domain.model.Restaurante;
import com.wjd.algafood.domain.repository.CozinhaRepository;
import com.wjd.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	public Restaurante buscar(final long restauranteId) {
		Restaurante restaurante = restauranteRepository.buscar(restauranteId);

		if (Objects.isNull(restaurante)) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de restaurante com o código %d", restauranteId));
		}

		return restaurante;
	}

	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());

		if (Objects.isNull(cozinha)) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não foi encontrado uma cozinha com o id %d", restaurante.getCozinha().getId()));
		}

		restaurante.setCozinha(cozinha);

		return restauranteRepository.salvar(restaurante);
	}

	public Restaurante atualizar(Restaurante restaurante, Long restauranteID) {
		Restaurante restauranteAtual = buscar(restauranteID);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
		
		return salvar(restauranteAtual);
	}
	
	public Restaurante atualizaParcial(Map<String, Object> campos, Long restauranteID) {
		Restaurante restauranteAtual = buscar(restauranteID);

		merge(campos, restauranteAtual);
		
		return salvar(restauranteAtual);
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
		
		camposOrigem.forEach((nome, valor) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nome);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
