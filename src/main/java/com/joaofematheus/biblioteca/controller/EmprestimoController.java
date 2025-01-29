package com.joaofematheus.biblioteca.controller;

import com.joaofematheus.biblioteca.dao.DAOEmprestimo;
import com.joaofematheus.biblioteca.model.Emprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/emprestimos")
public class EmprestimoController {

    @Autowired
    private DAOEmprestimo daoEmprestimo;

    @PostMapping
    public String adicionarEmprestimo(@RequestBody Emprestimo emprestimo) {
        try {
            daoEmprestimo.adicionar(emprestimo);
            return "Empréstimo adicionado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao adicionar empréstimo: " + e.getMessage();
        }
    }

    @GetMapping("/{id}")
    public Emprestimo buscarEmprestimoPorId(@PathVariable int id) {
        try {
            return daoEmprestimo.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimo: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Emprestimo> listarTodosEmprestimos() {
        try {
            return daoEmprestimo.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar empréstimos: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String removerEmprestimo(@PathVariable int id) {
        try {
            daoEmprestimo.remover(id);
            return "Empréstimo removido com sucesso!";
        } catch (SQLException e) {
            return "Erro ao remover empréstimo: " + e.getMessage();
        }
    }
}
