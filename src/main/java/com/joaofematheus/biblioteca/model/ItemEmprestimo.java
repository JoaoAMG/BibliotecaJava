package com.joaofematheus.biblioteca.model;

public class ItemEmprestimo {
	Integer id;
	Livro livro;
	Integer quantidade;
	Integer quantidadeaux;
	
	
	
	
	public ItemEmprestimo(Integer id, Livro livro, Integer quantidade,Integer quantidadeaux) {
		this.id = id;
		this.livro = livro;
		this.quantidade = quantidade;
		this.quantidadeaux = quantidade;
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
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidadeaux() {
		return quantidadeaux;
	}
	
	public void setQuantidadeaux(Integer quantidadeaux) {
		this.quantidadeaux = quantidadeaux;
	}
	

}
