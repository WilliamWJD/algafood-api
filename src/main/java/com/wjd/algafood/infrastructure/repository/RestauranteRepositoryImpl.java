package com.wjd.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.wjd.algafood.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		//Fabrica
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		 
		Predicate nomePredicate = builder.like(root.get("nome"), "%"+ nome+"%");
		Predicate taxaInicial = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
		Predicate taxaFinal = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
		
		criteria.where(nomePredicate, taxaInicial, taxaFinal);
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
	}
}
