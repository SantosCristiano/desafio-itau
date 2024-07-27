package br.com.itau.desafio.controller;

import br.com.itau.desafio.model.Produto;
import br.com.itau.desafio.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Validated
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        Produto novoProduto = produtoService.calcularPrecoTarifado(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        produto.setId(id);
        return produtoService.calcularPrecoTarifado(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }
}

