package com.joaofematheus.biblioteca.model;

public class ItemEmprestimo {
    private Integer id;
    private Livro livro;
    private Integer quantidade;
    private Integer quantidadeaux; 

    public ItemEmprestimo(Integer id, Livro livro, Integer quantidade) {
        this.id = id;
        this.livro = livro;
        setQuantidade(quantidade);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        
        if (this.quantidadeaux == null) {
            this.quantidadeaux = quantidade; 
        }
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeaux() {
        return quantidadeaux;
    }
}
