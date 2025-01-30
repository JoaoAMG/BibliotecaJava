package com.joaofematheus.biblioteca.service;

import com.joaofematheus.biblioteca.dao.DAOItemEmprestimo;
import com.joaofematheus.biblioteca.model.ItemEmprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemEmprestimoService {
    private final DAOItemEmprestimo daoItemEmprestimo;

    @Autowired
    public ItemEmprestimoService(DAOItemEmprestimo daoItemEmprestimo) {
        this.daoItemEmprestimo = daoItemEmprestimo;
    }

    public List<ItemEmprestimo> listarTodos() {
        try {
            return daoItemEmprestimo.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ItemEmprestimo buscarPorId(int id) {
        try {
            return daoItemEmprestimo.buscarPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void adicionarItemEmprestimo(ItemEmprestimo itemEmprestimo) {
        try {
            daoItemEmprestimo.adicionar(itemEmprestimo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarItemEmprestimo(ItemEmprestimo itemEmprestimo) {
        try {
            daoItemEmprestimo.atualizar(itemEmprestimo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarItemEmprestimo(int id) {
        try {
            daoItemEmprestimo.remover(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
