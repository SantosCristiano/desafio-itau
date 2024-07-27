package br.com.itau.desafio.repository;

import br.com.itau.desafio.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void testSaveAndFindById() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria("VIDA");
        produto.setPrecoBase(100.00);
        produto.setPrecoTarifado(103.20);

        Produto savedProduto = produtoRepository.save(produto);
        Optional<Produto> foundProduto = produtoRepository.findById(savedProduto.getId());

        assertThat(foundProduto).isPresent();
        assertThat(foundProduto.get().getNome()).isEqualTo("Seguro de Vida Individual");
    }

    @Test
    void testFindAll() {
        Produto produto1 = new Produto();
        produto1.setNome("Seguro de Vida Individual");
        produto1.setCategoria("VIDA");
        produto1.setPrecoBase(100.00);
        produto1.setPrecoTarifado(103.20);

        Produto produto2 = new Produto();
        produto2.setNome("Seguro de Auto");
        produto2.setCategoria("AUTO");
        produto2.setPrecoBase(50.00);
        produto2.setPrecoTarifado(55.25);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);

        Iterable<Produto> produtos = produtoRepository.findAll();
        assertThat(produtos).hasSize(2);
    }
}