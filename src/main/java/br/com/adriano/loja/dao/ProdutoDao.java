package br.com.adriano.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.adriano.loja.modelo.Categoria;
import br.com.adriano.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager entityManager;
	
	public ProdutoDao(EntityManager em) {
		this.entityManager=em;
	}
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}
	public void atualizar(Produto produto) {
		this.entityManager.merge(produto);
	}
	public void remover(Produto produto) {
		Produto c=entityManager.merge(produto);
		this.entityManager.remove(c);
	}
	public Produto buscarPorId(Long id) {
		return entityManager.find(Produto.class, id);
	}
	public List<Produto> buscarTodos(){
		// JPQL
		String jpql = "SELECT p FROM Produto p";
		return entityManager.createQuery(jpql,
				Produto.class).getResultList();
	}
	public List<Produto> buscarPorNome(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return entityManager.createQuery(jpql,Produto.class)
				.setParameter("nome",nome)
				.getResultList();
	}
	public List<Produto> buscarPorNomeDaCategoria(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return entityManager.createQuery(jpql,Produto.class)
				.setParameter("nome",nome)
				.getResultList();
//		return entityManager.createQuery("Produto.produtosPorCategoria",Produto.class)
//				.setParameter("nome",nome)
//				.getResultList();
	}
	public BigDecimal buscarPrecoDoProdutoPorNome(String nome){
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		
		return entityManager.createQuery(jpql,BigDecimal.class)
				.setParameter("nome",nome)
				.getSingleResult();
	}
}

