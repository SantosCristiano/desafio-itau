package br.com.itau.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;
    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;
    @NotNull(message = "Preço base é obrigatório")
    @JsonProperty("preco_base")
    private double precoBase;
    @JsonProperty("preco_tarifado")
    private double precoTarifado;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getPrecoTarifado() {
        return precoTarifado;
    }

    public void setPrecoTarifado(double precoTarifado) {
        this.precoTarifado = precoTarifado;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco_base=" + precoBase +
                ", preco_tarifado=" + precoTarifado +
                '}';
    }
}
