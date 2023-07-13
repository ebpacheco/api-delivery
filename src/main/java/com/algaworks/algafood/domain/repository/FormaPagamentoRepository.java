package com.algaworks.algafood.domain.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	@Override
	default void deleteById(Long id) {
		delete(findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1)));
	}
}
