package com.joaofematheus.biblioteca.service;

import com.joaofematheus.biblioteca.dao.DAOEmprestimo;
import com.joaofematheus.biblioteca.model.Emprestimo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private final DAOEmprestimo daoEmprestimo;

    public EmprestimoService(DAOEmprestimo daoEmprestimo) {
        this.daoEmprestimo = daoEmprestimo;
    }

    public List<Emprestimo> listarTodos() {
        try {
            return daoEmprestimo.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Emprestimo buscarPorId(int id) {
        try {
            return daoEmprestimo.buscarPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        try {
            daoEmprestimo.adicionar(emprestimo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarEmprestimo(int id) {
        try {
            daoEmprestimo.remover(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
