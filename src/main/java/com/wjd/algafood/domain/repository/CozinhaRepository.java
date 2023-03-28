package com.wjd.algafood.domain.repository;

import java.util.List;

import com.wjd.algafood.domain.model.Cozinha;

public interface CozinhaRepository {
	List<Cozinha> listar();
	Cozinha buscar(Long id);
	void salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);
}
