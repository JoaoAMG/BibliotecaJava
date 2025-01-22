package com.joaofematheus.biblioteca.model;

public class Livro {
	Integer id;
	String nome;
	String nomeautor;
	String editora;
	String anolancamento;
	Double valor;
	
	
	
	
	public Livro(Integer id,String nome, String nomeautor, String editora, String anolancamento, Double valor) {
		this.nome = nome;
		this.nomeautor = nomeautor;
		this.editora = editora;
		this.anolancamento = anolancamento;
		this.valor = valor;
	}
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeautor() {
		return nomeautor;
	}
	public void setNomeautor(String nomeautor) {
		this.nomeautor = nomeautor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getAnolancamento() {
		return anolancamento;
	}
	public void setAnolancamento(String anolancamento) {
		this.anolancamento = anolancamento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	

	
}
