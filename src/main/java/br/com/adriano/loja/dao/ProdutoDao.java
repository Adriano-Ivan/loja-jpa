package br.com.adriano.loja.dao;

import javax.persistence.EntityManager;

import br.com.adriano.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager entityManager;
	
	public ProdutoDao(EntityManager em) {
		this.entityManager=em;
	}
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}
}

