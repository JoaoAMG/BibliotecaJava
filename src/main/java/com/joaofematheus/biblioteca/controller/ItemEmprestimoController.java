package com.joaofematheus.biblioteca.controller;

import com.joaofematheus.biblioteca.dao.DAOItemEmprestimo;
import com.joaofematheus.biblioteca.model.ItemEmprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/itememprestimo")
public class ItemEmprestimoController {

    @Autowired
    private DAOItemEmprestimo daoItemEmprestimo;

    @GetMapping("/{id}")
    public ItemEmprestimo buscarItemPorId(@PathVariable int id) {
        try {
            return daoItemEmprestimo.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item: " + e.getMessage());
        }
    }

    @GetMapping
    public List<ItemEmprestimo> listarTodosItens() {
        try {
            return daoItemEmprestimo.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens: " + e.getMessage());
        }
    }
}
