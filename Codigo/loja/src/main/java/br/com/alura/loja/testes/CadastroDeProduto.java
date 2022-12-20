package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastraProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscaPorId(1l);
		System.out.println(p.getPreco());

// Testando metodo "buscarTodos"		
//		List<Produto> todos = produtoDao.buscarTodos();		
//		todos.forEach(p2 -> System.out.println(p2.getNome()));

// Testando metodo "buscarPorNome"
//		List<Produto> listaDeProdutoPeloNome = produtoDao.buscarPorNome("Xiaomi Redmi");
//		listaDeProdutoPeloNome.forEach(produtoPeloNome -> System.out.println(produtoPeloNome.getNome()));
		
// Testando metodo "buscarPorNomeDaCategoria"
//		List<Produto> listaDeProdutoDaCategoria = produtoDao.buscarPorNomeDaCategoria("CELULARES");
//		listaDeProdutoDaCategoria.forEach(produtoPelaCategoria -> System.out.println(produtoPelaCategoria.getNome()));
		
// Testando metodo "buscarPrecoDoProdutoComNome"
		BigDecimal precoDoProduto = produtoDao.buscaPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println(precoDoProduto);
		
	}

	private static void cadastraProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();

		produtoDao.cadastrar(celular);
		categoriaDao.cadastrar(celulares);

		em.getTransaction().commit();
		em.close();
	}

}
