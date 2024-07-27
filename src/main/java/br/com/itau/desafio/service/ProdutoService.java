package br.com.itau.desafio.service;

import br.com.itau.desafio.model.Produto;
import br.com.itau.desafio.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto calcularPrecoTarifado(Produto produto) {
        logger.info("Calculando preço tarifado para o produto: {}", produto.getNome());

        double precoBase = produto.getPrecoBase();
        double iof = 0.0;
        double pis = 0.0;
        double cofins = 0.0;

        switch (produto.getCategoria().toUpperCase()) {
            case "VIDA":
                iof = 0.01;
                pis = 0.022;
                break;
            case "AUTO":
                iof = 0.055;
                pis = 0.04;
                cofins = 0.01;
                break;
            case "VIAGEM":
                iof = 0.02;
                pis = 0.04;
                cofins = 0.01;
                break;
            case "RESIDENCIAL":
                iof = 0.04;
                cofins = 0.03;
                break;
            case "PATRIMONIAL":
                iof = 0.05;
                pis = 0.03;
                break;
            default:
                logger.warn("Categoria desconhecida: {}", produto.getCategoria());
        }

        double precoTarifado = precoBase + (precoBase * iof) + (precoBase * pis) + (precoBase * cofins);
        produto.setPrecoTarifado(precoTarifado);

        logger.info("Preço tarifado calculado para o produto {}: {}", produto.getNome(), produto.getPrecoTarifado());

        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
}
