package data_base_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final String URL = "jdbc:mysql://localhost:3306/gestaocasamento";
    private final String USUARIO = "root";
    private final String SENHA = "admin";
    private final String LOCAL = "America/Sao_Paulo";

    public Connection getConnection() {
        Properties properties = new Properties();
        properties.setProperty("user", USUARIO);
        properties.setProperty("password", SENHA);
        properties.setProperty("useSSL", "false");
        properties.setProperty("useTimezone", "true");
        properties.setProperty("serverTimezone", LOCAL);
        properties.setProperty("allowPublicKeyRetrieval", "true");

        try {
            System.out.println("Tentando estabelecer conexao com o banco de dados...");
            Connection connection = DriverManager.getConnection(URL, properties);
            System.out.println("Conexao estabelecida com sucesso!\n");
            return connection;
        } catch (SQLException e) {
            System.err.println("Falha ao estabelecer conexao com o banco de dados!");
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }
}

/*
package data_base_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public Connection getConnection()
    {
        try
        {
            System.out.println("Conexão Completa");
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "admin");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "America/Sao_Paulo");
            properties.setProperty("allowPublicKeyRetrieval", "true");

            String con = "jdbc:mysql://localhost:3306/gestaocasamento";
            return DriverManager.getConnection(con, properties);
            //Conexão bem sucedida!
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
*/