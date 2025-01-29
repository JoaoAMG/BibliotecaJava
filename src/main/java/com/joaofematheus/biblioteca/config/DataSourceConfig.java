package com.joaofematheus.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;

@Configuration
public class DataSourceConfig {

    private String url;
    private String user;
    private String password;

    public DataSourceConfig() {
        carregarPropriedades();
        criarTabelas(); 
    }

    private void carregarPropriedades() {
        try {
            Properties properties = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
                if (input == null) {
                    throw new RuntimeException("Arquivo application.properties não encontrado!");
                }
                properties.load(input);
            }

            url = properties.getProperty("spring.datasource.url");
            user = properties.getProperty("spring.datasource.username");
            password = properties.getProperty("spring.datasource.password");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo application.properties: " + e.getMessage());
        }
    }

    @Bean
    public DataSource dataSource() {
        return new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(url, user, password);
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection(url, username, password);
            }

            @Override
            public <T> T unwrap(Class<T> iface) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) {
                return false;
            }

            @Override
            public PrintWriter getLogWriter() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public void setLogWriter(PrintWriter out) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public void setLoginTimeout(int seconds) {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public int getLoginTimeout() {
                throw new UnsupportedOperationException("Not implemented");
            }

            @Override
            public java.util.logging.Logger getParentLogger() {
                throw new UnsupportedOperationException("Not implemented");
            }
        };
    }

    private void criarTabelas() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Conexão com o banco de dados bem-sucedida!");

            // Criar tabela Livro primeiro
            String createLivroTable = """
                CREATE TABLE IF NOT EXISTS Livro (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    nomeautor VARCHAR(255) NOT NULL,
                    editora VARCHAR(255),
                    anolancamento VARCHAR(10),
                    valor DECIMAL(10, 2)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            """;
            statement.executeUpdate(createLivroTable);

            
            String createEmprestimoTable = """
                CREATE TABLE IF NOT EXISTS Emprestimo (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    numerodias INT NOT NULL
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            """;
            statement.executeUpdate(createEmprestimoTable);

           
            String createItemEmprestimoTable = """
                CREATE TABLE IF NOT EXISTS ItemEmprestimo (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    livro_id INT NOT NULL,
                    emprestimo_id INT NOT NULL,
                    quantidade INT NOT NULL,
                    FOREIGN KEY (livro_id) REFERENCES Livro(id) ON DELETE CASCADE,
                    FOREIGN KEY (emprestimo_id) REFERENCES Emprestimo(id) ON DELETE CASCADE
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            """;
            statement.executeUpdate(createItemEmprestimoTable);

            System.out.println("Tabelas criadas com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados ou criar tabelas: " + e.getMessage());
        }
    }


}
