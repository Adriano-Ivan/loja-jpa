package br.com.adriano.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	public List<Produto> buscaPorParametrosComCriteria(String nome,
			BigDecimal preco, LocalDate dataCadastro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> query =  builder
				.createQuery(Produto.class);
		Root<Produto> from =  query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if(nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"),
					nome));
		}
		if(preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"),
					preco));
		}
		if(dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"),
					dataCadastro));
		}
		query.where(filtros);
		return entityManager.createQuery(query)
				.getResultList();
	}
//	public List<Produto> buscarPorParametros(String nome,
//			BigDecimal preco, LocalDate dataCadastro){
//		String jpql="SELECT p FROM Produto p WHERE 1=1 ";
//		if(nome != null && !nome.trim().isEmpty()) {
//			jpql+="AND p.nome = :nome ";
//		}
//		if(preco != null) {
//			jpql+="AND p.preco = :preco ";
//		}
//		if(dataCadastro != null) {
//			jpql+="AND p.dataCadastro = :dataCadastro ";
//		}
//		
//		TypedQuery<Produto> query =entityManager
//				.createQuery(jpql,Produto.class);
//		
//		if(nome != null && !nome.trim().isEmpty()) {
//			query.setParameter("nome", nome);
//		}
//		if(preco != null) {
//			query.setParameter("preco", preco);
//		}
//		if(dataCadastro != null) {
//			query.setParameter("dataCadastro",dataCadastro);
//		}
//		return query.getResultList();
//	}
	
}

