package com.joaofematheus.biblioteca.dao;

import com.joaofematheus.biblioteca.model.ItemEmprestimo;
import com.joaofematheus.biblioteca.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DAOItemEmprestimo {

    private final DataSource dataSource;
    private final DAOLivro daoLivro; 

    @Autowired
    public DAOItemEmprestimo(DataSource dataSource, DAOLivro daoLivro) {
        this.dataSource = dataSource;
        this.daoLivro = daoLivro;
    }

    
    public void adicionar(ItemEmprestimo item) throws SQLException {
        String query = "INSERT INTO ItemEmprestimo (livro_id, emprestimo_id, quantidade) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

        	stmt.setInt(1, item.getId());
            stmt.setInt(2, item.getLivro().getId());
            stmt.setInt(3, item.getQuantidade());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(generatedKeys.getInt(1)); 
            }
        }
    }

    
    public ItemEmprestimo buscarPorId(int id) throws SQLException {
        String query = "SELECT * FROM ItemEmprestimo WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro livro = daoLivro.buscarPorId(rs.getInt("livro_id"));

                return new ItemEmprestimo(
                        rs.getInt("id"),
                        livro,
                        rs.getInt("quantidade")
                );
            }
        }
        return null;
    }

    
    public List<ItemEmprestimo> listarTodos() throws SQLException {
        List<ItemEmprestimo> itemEmprestimolist = new ArrayList<>();
        String query = "SELECT * FROM ItemEmprestimo";

        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Livro livro = daoLivro.buscarPorId(rs.getInt("livro_id"));

                itemEmprestimolist.add(new ItemEmprestimo(
                        rs.getInt("id"),
                        livro,
                        rs.getInt("quantidade")
                ));
            }
        }
        return itemEmprestimolist;
    }

    
    public void atualizar(ItemEmprestimo item) throws SQLException {
        String query = """
            UPDATE ItemEmprestimo 
            SET livro_id = ?,  quantidade = ? 
            WHERE id = ?
        """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
        	
        	stmt.setInt(1, item.getId());
            stmt.setInt(2, item.getLivro().getId());
            stmt.setInt(3, item.getQuantidade());

            stmt.executeUpdate();
        }
    }

   
    public void remover(int id) throws SQLException {
        String query = "DELETE FROM ItemEmprestimo WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
