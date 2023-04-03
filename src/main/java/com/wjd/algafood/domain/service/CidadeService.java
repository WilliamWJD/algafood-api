package com.wjd.algafood.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjd.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.wjd.algafood.domain.model.Cidade;
import com.wjd.algafood.domain.model.Estado;
import com.wjd.algafood.domain.repository.CidadeRepository;
import com.wjd.algafood.domain.repository.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> listar() {
		return cidadeRepository.listar();
	}

	public Cidade buscar(final Long cidadeId) {
		Cidade Cidade = cidadeRepository.buscar(cidadeId);

		if (Objects.isNull(Cidade)) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade não encontrado com o id %d", cidadeId));
		}

		return Cidade;
	}

	public Cidade salvar(final Cidade cidade) {
		Estado estado = estadoRepository.buscar(cidade.getEstado().getId());

		if (Objects.isNull(estado)) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado não encontrado com o id %d", cidade.getEstado().getId()));
		}

		return cidadeRepository.salvar(cidade);
	}

	public Cidade atualizar(final Cidade cidade, final Long cidadeId) {
		Cidade cidadeAtual = buscar(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		return salvar(cidadeAtual);
	}
}
