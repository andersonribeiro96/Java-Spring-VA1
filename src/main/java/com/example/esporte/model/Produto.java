package com.example.esporte.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o nome do Produto")
    private String nome;

    @ManyToOne
    private Marca marca;

    @NotBlank(message = "Informe o modelo do Produto")
    private String modelo;

    private String imagem;

    @NotNull(message = "Informe a quantidade de produtos")
    private Long quantidade;
    private Long valor;

    public Set<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(Set<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }



    @ManyToMany
    @JoinTable(
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> listaCategoria;


    public Set<Categoria> getCategoria() {
        return listaCategoria;
    }

    public void setCategoria(Set<Categoria> categoria) {
        this.listaCategoria = categoria;
    }



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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}
