package br.com.itau.desafio.service;

import br.com.itau.desafio.model.Produto;
import br.com.itau.desafio.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalcularPrecoTarifadoVida() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria("VIDA");
        produto.setPrecoBase(100.00);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setNome("Seguro de Vida Individual");
        produtoSalvo.setCategoria("VIDA");
        produtoSalvo.setPrecoBase(100.00);
        produtoSalvo.setPrecoTarifado(103.20);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        Produto salvo = produtoService.calcularPrecoTarifado(produto);
        assertNotNull(salvo);
        assertEquals(103.20, salvo.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testCalcularPrecoTarifadoAuto() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Auto");
        produto.setCategoria("AUTO");
        produto.setPrecoBase(50.00);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setNome("Seguro de Auto");
        produtoSalvo.setCategoria("AUTO");
        produtoSalvo.setPrecoBase(50.00);
        produtoSalvo.setPrecoTarifado(55.25);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        Produto salvo = produtoService.calcularPrecoTarifado(produto);
        assertNotNull(salvo);
        assertEquals(55.25, salvo.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testCalcularPrecoTarifadoViagem() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Viagem");
        produto.setCategoria("VIAGEM");
        produto.setPrecoBase(200.00);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setNome("Seguro de Viagem");
        produtoSalvo.setCategoria("VIAGEM");
        produtoSalvo.setPrecoBase(200.00);
        produtoSalvo.setPrecoTarifado(210.20);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        Produto salvo = produtoService.calcularPrecoTarifado(produto);
        assertNotNull(salvo);
        assertEquals(210.20, salvo.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testCalcularPrecoTarifadoResidencial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Residencial");
        produto.setCategoria("RESIDENCIAL");
        produto.setPrecoBase(300.00);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setNome("Seguro Residencial");
        produtoSalvo.setCategoria("RESIDENCIAL");
        produtoSalvo.setPrecoBase(300.00);
        produtoSalvo.setPrecoTarifado(312.00);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        Produto salvo = produtoService.calcularPrecoTarifado(produto);
        assertNotNull(salvo);
        assertEquals(312.00, salvo.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testCalcularPrecoTarifadoPatrimonial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Patrimonial");
        produto.setCategoria("PATRIMONIAL");
        produto.setPrecoBase(400.00);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setNome("Seguro Patrimonial");
        produtoSalvo.setCategoria("PATRIMONIAL");
        produtoSalvo.setPrecoBase(400.00);
        produtoSalvo.setPrecoTarifado(432.00);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        Produto salvo = produtoService.calcularPrecoTarifado(produto);
        assertNotNull(salvo);
        assertEquals(432.00, salvo.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testListarProdutos() {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Seguro de Vida Individual");
        produto1.setCategoria("VIDA");
        produto1.setPrecoBase(100.00);

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Seguro de Auto");
        produto2.setCategoria("AUTO");
        produto2.setPrecoBase(50.00);

        when(produtoRepository.findAll()).thenReturn(List.of(produto1, produto2));

        List<Produto> produtos = produtoService.listarProdutos();
        assertNotNull(produtos);
        assertEquals(2, produtos.size());
        verify(produtoRepository, times(1)).findAll();
    }
}
