package com.wjd.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Component;

import com.wjd.algafood.domain.model.Restaurante;
import com.wjd.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	} 
	
	@Override
	@Transactional
	public Restaurante salvar(Restaurante cozinha) {
		return manager.merge(cozinha);
	}

	@Override
	@Transactional
	public void remover(Restaurante cozinha) {
		manager.remove(buscar(cozinha.getId()));
	}

}
