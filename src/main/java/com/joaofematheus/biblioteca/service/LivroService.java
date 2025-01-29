package com.joaofematheus.biblioteca.service;

import com.joaofematheus.biblioteca.dao.DAOLivro;
import com.joaofematheus.biblioteca.model.Livro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private final DAOLivro daoLivro;

    public LivroService(DAOLivro daoLivro) {
        this.daoLivro = daoLivro;
    }

    public List<Livro> listarTodos() {
        try {
            return daoLivro.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace(); 
            return new ArrayList<>(); 
        }
    }

    public Livro buscarPorId(int id) {
        try {
            return daoLivro.buscarPorId(id);
        } catch (SQLException e) {
            e.printStackTrace(); 
            return null; 
        }
    }

    public void adicionarLivro(Livro livro) {
        try {
            daoLivro.adicionar(livro);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public void atualizarLivro(Livro livro) {
        try {
            daoLivro.atualizar(livro);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public void deletarLivro(int id) {
        try {
            daoLivro.remover(id);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
