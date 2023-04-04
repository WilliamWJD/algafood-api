package com.wjd.algafood.domain.service;

import java.util.List;

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
		return cidadeRepository.findAll();
	}

	public Cidade buscar(final Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Cidade não encontrado com o id %d", cidadeId)));
	}

	public Cidade salvar(final Cidade cidade) {
		Estado estado = estadoRepository.findById(cidade.getEstado().getId()).orElseThrow(()->new EntidadeNaoEncontradaException(
					String.format("Estado não encontrado com o id %d", cidade.getEstado().getId())));

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	public Cidade atualizar(final Cidade cidade, final Long cidadeId) {
		Cidade cidadeAtual = buscar(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		return salvar(cidadeAtual);
	}
}
