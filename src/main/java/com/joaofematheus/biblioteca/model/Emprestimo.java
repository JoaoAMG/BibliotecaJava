package com.joaofematheus.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Emprestimo {
	Integer id;
	List<ItemEmprestimo> livros = new ArrayList<>();
	Integer numerodias;
	
	
	
	public Emprestimo(Integer id, List<ItemEmprestimo> livros, Integer numerodias) {
		this.id = id;
		this.livros = livros;
		this.numerodias = numerodias;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<ItemEmprestimo> getLivros() {
		return livros;
	}
	public void setLivros(List<ItemEmprestimo> livros) {
		this.livros = livros;
	}
	public Integer getNumerodias() {
		return numerodias;
	}
	public void setNumerodias(Integer numerodias) {
		this.numerodias = numerodias;
	}
	
	
	
	

}
