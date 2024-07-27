package br.com.itau.desafio.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGettersAndSetters() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria("VIDA");
        produto.setPrecoBase(100.00);
        produto.setPrecoTarifado(103.20);

        assertEquals(1L, produto.getId());
        assertEquals("Seguro de Vida Individual", produto.getNome());
        assertEquals("VIDA", produto.getCategoria());
        assertEquals(100.00, produto.getPrecoBase());
        assertEquals(103.20, produto.getPrecoTarifado());
    }

    @Test
    void testToString() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria("VIDA");
        produto.setPrecoBase(100.00);
        produto.setPrecoTarifado(103.20);

        String expectedString = "Produto{" +
                "id=" + 1L +
                ", nome='Seguro de Vida Individual'" +
                ", categoria='VIDA'" +
                ", preco_base=" + 100.00 +
                ", preco_tarifado=" + 103.20 +
                '}';

        assertEquals(expectedString, produto.toString());
    }

    @Test
    void testValidations() {
        Produto produto = new Produto();
        produto.setNome(null); // Testing NotBlank annotation
        produto.setCategoria(null); // Testing NotBlank annotation
        produto.setPrecoBase(0.0); // Testing NotNull annotation

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);
        assertFalse(violations.isEmpty());

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Nome do produto é obrigatório")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Categoria é obrigatória")));

        produto.setNome("Produto Teste");
        produto.setCategoria("Categoria Teste");
        produto.setPrecoBase(100.00);

        violations = validator.validate(produto);
        assertTrue(violations.isEmpty());
    }
}
