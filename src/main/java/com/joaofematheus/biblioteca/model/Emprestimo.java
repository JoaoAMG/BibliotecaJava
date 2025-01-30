package com.joaofematheus.biblioteca.model;
import java.util.List;

public class Emprestimo {
    private int id;
    private int numerodias;
    private List<ItemEmprestimo> livros; 

   
    public Emprestimo(int id, int numerodias, List<ItemEmprestimo> livros) {
        this.id = id;
        this.numerodias = numerodias;
        this.livros = livros;
    }
    public Emprestimo() {
    }

    
    public Emprestimo(int id, int numerodias) {
        this.id = id;
        this.numerodias = numerodias;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroDias() {
        return numerodias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numerodias = numeroDias;
    }

    public List<ItemEmprestimo> getLivros() {
        return livros;
    }

    public void setLivros(List<ItemEmprestimo> livros) {
        this.livros = livros;
    }
}
