package com.joaofematheus.biblioteca.dao;

import com.joaofematheus.biblioteca.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DAOLivro {

    private final DataSource dataSource;

    @Autowired
    public DAOLivro(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void adicionar(Livro livro) throws SQLException {
        String query = "INSERT INTO Livro (nome, nomeautor, editora, anolancamento, valor) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getNomeautor());
            stmt.setString(3, livro.getEditora());
            stmt.setString(4, livro.getAnolancamento());
            stmt.setDouble(5, livro.getValor());
            stmt.executeUpdate();
        }
    }
    
    public void atualizar(Livro livro) throws SQLException {
        String query = """
            UPDATE Livro 
            SET nome = ?, 
                nomeautor = ?, 
                editora = ?, 
                anolancamento = ?, 
                valor = ? 
            WHERE id = ?
        """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
       
            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getNomeautor());
            stmt.setString(3, livro.getEditora());
            stmt.setString(4, livro.getAnolancamento());
            stmt.setDouble(5, livro.getValor());
            stmt.setInt(6, livro.getId());

         
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Livro atualizado com sucesso! ID: " + livro.getId());
            } else {
                System.out.println("Nenhum livro encontrado para atualizar com o ID: " + livro.getId());
            }
        }
    }

    
    
    
    

    public List<Livro> listarTodos() throws SQLException {
        String query = "SELECT * FROM Livro";
        List<Livro> livros = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("nomeautor"),
                        rs.getString("editora"),
                        rs.getString("anolancamento"),
                        rs.getDouble("valor")
                );
                livros.add(livro);
            }
        }
        return livros;
    }

    public Livro buscarPorId(int id) throws SQLException {
        String query = "SELECT * FROM Livro WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("nomeautor"),
                            rs.getString("editora"),
                            rs.getString("anolancamento"),
                            rs.getDouble("valor")
                    );
                }
            }
        }
        return null;
    }
    
    public void remover(int id) throws SQLException {
        String query = "DELETE FROM Livro WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
