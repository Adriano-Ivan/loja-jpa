package br.com.adriano.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.adriano.loja.modelo.Categoria;
import br.com.adriano.loja.modelo.Pedido;
import br.com.adriano.loja.modelo.Produto;
import br.com.adriano.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager entityManager;
	
	public PedidoDao(EntityManager em) {
		this.entityManager=em;
	}
	public void cadastrar(Pedido pedido) {
		this.entityManager.persist(pedido);
	}
	public BigDecimal valorTotalVendido() {
		String jpql="SELECT SUM(p.valorTotal) FROM Pedido p";
		return entityManager.createQuery(jpql,
				BigDecimal.class).getSingleResult();
	}
	public List<RelatorioDeVendasVo> relatorioDeVendas(){
		String jpql="SELECT new br.com.adriano.loja.vo.RelatorioDeVendasVo(produto.nome,SUM(item.quantidade),"
				+"MAX(pedido.data)) "
				+"FROM Pedido pedido "
				+"JOIN pedido.itens item "
				+"JOIN item.produto produto "
				+"GROUP BY produto.nome "
				+"ORDER BY item.quantidade DESC";
		
		return entityManager.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
	public Pedido buscarPedidoPorCliente(Long id) {
		return entityManager.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id",Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}

