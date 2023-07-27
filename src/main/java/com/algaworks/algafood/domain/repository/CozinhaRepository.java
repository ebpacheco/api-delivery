package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	List<Cozinha> findTodosByNomeContaining(String nome);

	Optional<Cozinha> findByNome(String nome);

	boolean existsCozinhaByNome(String nome);

//	@Override
//	default void deleteById(Long id) {
//		delete(findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1)));
//	}

}
