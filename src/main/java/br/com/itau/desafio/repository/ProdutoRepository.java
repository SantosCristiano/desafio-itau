package br.com.itau.desafio.repository;

import br.com.itau.desafio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
