package br.com.itau.desafio;

import br.com.itau.desafio.model.Produto;
import br.com.itau.desafio.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void testCriarProduto() throws Exception {
        MvcResult result = mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Seguro de Vida Individual\", \"categoria\": \"VIDA\", \"preco_base\": 100.00}"))
                .andExpect(status().isCreated())
                .andReturn();

        Optional<Produto> produtoSalvo = Optional.empty();
        produtoSalvo = produtoRepository.findById(3L);
        assertThat(produtoSalvo).isPresent();
        double precoTarifado = produtoSalvo.get().getPrecoTarifado();
        assertThat(precoTarifado).isEqualTo(103.20);
    }

    @Test
    void testListarProdutos() throws Exception {
        Produto produto1 = new Produto();
        produto1.setNome("Seguro de Vida Individual");
        produto1.setCategoria("VIDA");
        produto1.setPrecoBase(100.00);
        produtoRepository.save(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("Seguro de Auto");
        produto2.setCategoria("AUTO");
        produto2.setPrecoBase(50.00);
        produtoRepository.save(produto2);

        mockMvc.perform(get("/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Seguro de Vida Individual"))
                .andExpect(jsonPath("$[1].nome").value("Seguro de Auto"));
    }
}
