package br.com.adriano.loja.dao;

import javax.persistence.EntityManager;

import br.com.adriano.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager entityManager;
	
	public CategoriaDao(EntityManager em) {
		this.entityManager=em;
	}
	public void cadastrar(Categoria categoria) {
		entityManager.persist(categoria);
	}
	public void atualizar(Categoria categoria) {
		this.entityManager.merge(categoria);
	}
	public void remover(Categoria categoria) {
		Categoria c=entityManager.merge(categoria);
		this.entityManager.remove(c);
	}
}
