package br.com.adriano.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.adriano.loja.dao.CategoriaDao;
import br.com.adriano.loja.dao.ClienteDao;
import br.com.adriano.loja.dao.PedidoDao;
import br.com.adriano.loja.dao.ProdutoDao;
import br.com.adriano.loja.modelo.Categoria;
import br.com.adriano.loja.modelo.Cliente;
import br.com.adriano.loja.modelo.ItemPedido;
import br.com.adriano.loja.modelo.Pedido;
import br.com.adriano.loja.modelo.Produto;
import br.com.adriano.loja.util.JPAUtil;
import br.com.adriano.loja.vo.RelatorioDeVendasVo;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		List<Produto> produto =produtoDao
				.buscaPorParametrosComCriteria("O Senhor Dos Anéis",
				null,null);
		
		em.close();
		System.out.println(produto.get(0).getNome());
	}
	private static void popularBancoDeDados() {
		Categoria livros = new Categoria("LIVROS");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		Categoria celulares=new Categoria("CELULARES");
		
		Produto livro = new Produto("O Senhor Dos Anéis",
				"Interessante 44000",new BigDecimal("830"),
				livros);
		Produto videogame = new Produto("EX",
				"Interessante 488",new BigDecimal("890"),
				videogames);
		Produto notebook = new Produto("Not - EX",
				"Interessante 22233",new BigDecimal("2480"),
				informatica);
		Produto celular = new Produto("C - EX",
				"Interessante 22233",new BigDecimal("982"),
				celulares);
		
		Cliente cliente = new Cliente("Rold","121232");
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(livros);
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(informatica);
		categoriaDao.cadastrar(videogames);
		
		produtoDao.cadastrar(livro);
		produtoDao.cadastrar(notebook);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(celular);
		
		clienteDao.cadastrar(cliente);
		
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
