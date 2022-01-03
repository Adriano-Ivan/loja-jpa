package br.com.adriano.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.adriano.loja.dao.CategoriaDao;
import br.com.adriano.loja.dao.ProdutoDao;
import br.com.adriano.loja.modelo.Categoria;
import br.com.adriano.loja.modelo.Produto;
import br.com.adriano.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getId());
		
		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2->System.out.println(p2.getNome()));
		
		System.out.println("Por nome");
		List<Produto> todosPorNome=produtoDao.buscarPorNome("Xiaomi Redmi");
		todosPorNome.forEach(pn->System.out.println(pn.getNome()));
	
		System.out.println("Por nome da categoria");
		List<Produto> todosPorNomeDaCategoria=produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todosPorNomeDaCategoria.forEach(pnc->System.out.println(pnc.getNome()));
	
		BigDecimal precoDoProduto=produtoDao.buscarPrecoDoProdutoPorNome("O Senhor Dos Anéis");
		
		System.out.println(precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("LIVROS");
		
		Produto celular = new Produto("O Senhor Dos Anéis",
				"Interessante 44000",new BigDecimal("830"),
				celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
//		em.getTransaction().begin();
//		
//		em.persist(celulares);
//		celulares.setNome("EITA");
//		
//		em.flush();
//		em.clear();
//		
//		celulares = em.merge(celulares);
//		celulares.setNome("EITA 2");
//		em.flush();
//		em.clear();
//		em.remove(celulares);
//		em.flush();
//		

	}
}
