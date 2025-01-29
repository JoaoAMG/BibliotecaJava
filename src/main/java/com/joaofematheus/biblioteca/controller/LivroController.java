package com.joaofematheus.biblioteca.controller;

import com.joaofematheus.biblioteca.dao.DAOLivro;
import com.joaofematheus.biblioteca.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/livros")
public class LivroController {

    @Autowired
    private DAOLivro daoLivro;

    @PostMapping
    public String adicionarLivro(@RequestBody Livro livro) {
        try {
            daoLivro.adicionar(livro);
            return "Livro adicionado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao adicionar livro: " + e.getMessage();
        }
    }

    @GetMapping("/{id}")
    public Livro buscarLivroPorId(@PathVariable int id) {
        try {
            return daoLivro.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Livro> listarTodosLivros() {
        try {
            return daoLivro.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String removerLivro(@PathVariable int id) {
        try {
            daoLivro.remover(id);
            return "Livro removido com sucesso!";
        } catch (SQLException e) {
            return "Erro ao remover livro: " + e.getMessage();
        }
    }
}
