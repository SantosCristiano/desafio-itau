package br.com.itau.desafio.controller;

import br.com.itau.desafio.model.Produto;
import br.com.itau.desafio.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCriarProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria("VIDA");
        produto.setPrecoBase(100.00);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(1L);
        produtoSalvo.setNome("Seguro de Vida Individual");
        produtoSalvo.setCategoria("VIDA");
        produtoSalvo.setPrecoBase(100.00);
        produtoSalvo.setPrecoTarifado(103.20);

        when(produtoService.calcularPrecoTarifado(any(Produto.class))).thenReturn(produtoSalvo);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Seguro de Vida Individual"))
                .andExpect(jsonPath("$.categoria").value("VIDA"))
                .andExpect(jsonPath("$.preco_base").value(100.00))
                .andExpect(jsonPath("$.preco_tarifado").value(103.20));

        verify(produtoService, times(1)).calcularPrecoTarifado(any(Produto.class));
    }

    @Test
    void testAtualizarProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria("VIDA");
        produto.setPrecoBase(100.00);

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(1L);
        produtoAtualizado.setNome("Seguro de Vida Individual");
        produtoAtualizado.setCategoria("VIDA");
        produtoAtualizado.setPrecoBase(100.00);
        produtoAtualizado.setPrecoTarifado(103.20);

        when(produtoService.calcularPrecoTarifado(any(Produto.class))).thenReturn(produtoAtualizado);

        mockMvc.perform(put("/produtos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Seguro de Vida Individual"))
                .andExpect(jsonPath("$.categoria").value("VIDA"))
                .andExpect(jsonPath("$.preco_base").value(100.00))
                .andExpect(jsonPath("$.preco_tarifado").value(103.20));

        verify(produtoService, times(1)).calcularPrecoTarifado(any(Produto.class));
    }

    @Test
    void testListarProdutos() throws Exception {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Seguro de Vida Individual");
        produto1.setCategoria("VIDA");
        produto1.setPrecoBase(100.00);
        produto1.setPrecoTarifado(103.20);

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Seguro de Auto");
        produto2.setCategoria("AUTO");
        produto2.setPrecoBase(50.00);
        produto2.setPrecoTarifado(55.25);

        when(produtoService.listarProdutos()).thenReturn(List.of(produto1, produto2));

        mockMvc.perform(get("/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Seguro de Vida Individual"))
                .andExpect(jsonPath("$[0].categoria").value("VIDA"))
                .andExpect(jsonPath("$[0].preco_base").value(100.00))
                .andExpect(jsonPath("$[0].preco_tarifado").value(103.20))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Seguro de Auto"))
                .andExpect(jsonPath("$[1].categoria").value("AUTO"))
                .andExpect(jsonPath("$[1].preco_base").value(50.00))
                .andExpect(jsonPath("$[1].preco_tarifado").value(55.25));

        verify(produtoService, times(1)).listarProdutos();
    }

    @Test
    void testCriarProdutoBadRequest() throws Exception {
        Produto produto = new Produto();
        produto.setNome("");
        produto.setCategoria("VIDA");
        produto.setPrecoBase(100.00);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isBadRequest());
    }
}
