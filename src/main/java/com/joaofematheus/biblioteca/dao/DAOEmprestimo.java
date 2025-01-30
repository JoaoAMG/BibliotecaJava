package com.joaofematheus.biblioteca.dao;

import com.joaofematheus.biblioteca.model.Emprestimo;
import com.joaofematheus.biblioteca.model.ItemEmprestimo;
import com.joaofematheus.biblioteca.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DAOEmprestimo {

    private final DataSource dataSource;

    @Autowired
    public DAOEmprestimo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void adicionar(Emprestimo emprestimo) throws SQLException {
        String query = "INSERT INTO Emprestimo (numerodias) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, emprestimo.getNumeroDias());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int emprestimoId = generatedKeys.getInt(1);
                if (emprestimo.getLivros() != null) { 
                    for (ItemEmprestimo item : emprestimo.getLivros()) {
                        adicionarItemEmprestimo(connection, emprestimoId, item);
                    }
                }
            }
        }
    }


    private void adicionarItemEmprestimo(Connection connection, int emprestimoId, ItemEmprestimo item) throws SQLException {
        String query = "INSERT INTO ItemEmprestimo (emprestimo_id, livro_id, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, emprestimoId);
            stmt.setInt(2, item.getLivro().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public Emprestimo buscarPorId(int id) throws SQLException {
        String query = "SELECT * FROM Emprestimo WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    List<ItemEmprestimo> itens = buscarItensPorEmprestimoId(id, connection);
                    return new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("numerodias"),
                        itens 
                    );
                }
            }
        }
        return null;
    }

    private List<ItemEmprestimo> buscarItensPorEmprestimoId(int emprestimoId, Connection connection) throws SQLException {
        String query = "SELECT * FROM ItemEmprestimo WHERE emprestimo_id = ?";
        List<ItemEmprestimo> itens = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, emprestimoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int livroId = rs.getInt("livro_id");
                    Livro livro = buscarLivroPorId(livroId, connection);
                    
                    
                    Emprestimo emprestimo = new Emprestimo();
                    emprestimo.setId(emprestimoId);

                    
                    ItemEmprestimo item = new ItemEmprestimo(
                        rs.getInt("id"),
                        livro,
                        emprestimo, 
                        rs.getInt("quantidade")
                    );
                    itens.add(item);
                }
            }
        }
        return itens;
    }

    private Livro buscarLivroPorId(int livroId, Connection connection) throws SQLException {
        String query = "SELECT * FROM Livro WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, livroId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("nomeautor"),
                        rs.getString("editora"),
                        rs.getString("anolancamento"),
                        rs.getDouble("Valor")
                    );
                }
            }
        }
        return null;
    }

    public List<Emprestimo> listarTodos() throws SQLException {
        String query = "SELECT * FROM Emprestimo";
        List<Emprestimo> emprestimos = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                List<ItemEmprestimo> itens = buscarItensPorEmprestimoId(rs.getInt("id"), connection);
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("numerodias"),
                        itens
                );
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public void remover(int id) throws SQLException {
        String query = "DELETE FROM Emprestimo WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
