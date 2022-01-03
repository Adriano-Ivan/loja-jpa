package br.com.adriano.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.adriano.loja.modelo.Categoria;
import br.com.adriano.loja.modelo.Cliente;
import br.com.adriano.loja.modelo.Pedido;
import br.com.adriano.loja.modelo.Produto;

public class ClienteDao {

	private EntityManager entityManager;
	
	public ClienteDao(EntityManager em) {
		this.entityManager=em;
	}
	public void cadastrar(Cliente cliente) {
		this.entityManager.persist(cliente);
	}
	public Cliente buscarPorId(Long id) {
		return entityManager.find(Cliente.class, id);
	}
}

