package br.com.adriano.loja.testes;

import java.math.BigDecimal;
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

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		ProdutoDao produtoDao2 = new ProdutoDao(em);
		ClienteDao clienteDao2 = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Cliente cliente2 = clienteDao.buscarPorId(2l);
		
		em.getTransaction().begin();
		
		Pedido pedido = new Pedido(cliente);
		Pedido pedido2 = new Pedido(cliente2);
		pedido.adicionarItem(new ItemPedido(10,pedido,produto));
		pedido.adicionarItem(new ItemPedido(10,pedido2,produto2));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL: "+totalVendido);
	
		List<RelatorioDeVendasVo> relatorio=pedidoDao.relatorioDeVendas();

		relatorio.forEach(System.out::println);
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
