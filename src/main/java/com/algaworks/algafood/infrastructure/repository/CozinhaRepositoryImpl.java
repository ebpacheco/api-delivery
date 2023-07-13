//package com.algaworks.algafood.infrastructure.repository;
//
//import java.util.List;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import com.algaworks.algafood.domain.model.Cozinha;
//import com.algaworks.algafood.domain.repository.CozinhaRepository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import jakarta.transaction.Transactional;
//
//@Repository
//public class CozinhaRepositoryImpl implements CozinhaRepository {
//
//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	public List<Cozinha> listar() {
//		TypedQuery<Cozinha> query = manager.createQuery("FROM Cozinha", Cozinha.class);
//		return query.getResultList();
////		return manager.createQuery("FROM Cozinha", Cozinha.class).getResultList();
//	}
//
//	@Override
//	public List<Cozinha> buscarPorNome(String nome) {
//		return manager.createQuery("FROM Cozinha WHERE nome LIKE :nome", Cozinha.class)
//				.setParameter("nome", "%" + nome + "%").getResultList();
//	}
//
//	@Override
//	public Cozinha buscar(Long id) {
//		return manager.find(Cozinha.class, id);
//	}
//
//	@Transactional
//	@Override
//	public Cozinha salvar(Cozinha cozinha) {
//		return manager.merge(cozinha);
//	}
//
//	@Transactional
//	@Override
//	public void remover(Long id) {
//		Cozinha cozinha = buscar(id);
//
//		if (cozinha == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		manager.remove(cozinha);
//	}
//
//}
