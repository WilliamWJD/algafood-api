package com.wjd.algafood.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjd.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.wjd.algafood.domain.model.Estado;
import com.wjd.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.listar();
	}

	public Estado buscar(final Long estadoId) {
		Estado estado = estadoRepository.buscar(estadoId);

		if (Objects.isNull(estado)) {
			throw new EntidadeNaoEncontradaException(String.format("Estado não encontrado com o id %d", estadoId));
		}

		return estado;
	}

	public Estado salvar(final Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public Estado atualizar(final Estado estado, final Long estadoId) {
		Estado estadoAtual = buscar(estadoId);
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		
		return salvar(estadoAtual);
	}
}
