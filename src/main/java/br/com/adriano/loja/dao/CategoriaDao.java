package br.com.adriano.loja.dao;

import javax.persistence.EntityManager;

import br.com.adriano.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em=em;
	}
	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}
}
