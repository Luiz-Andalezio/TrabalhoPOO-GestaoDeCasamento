package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartorioDAO {

    public CartorioDAO() {
        Cartorio c = new Cartorio();
        c.setNome("Cartório Primeiro Distrito da Capital");
        c.setEndereco("Av. Marquês de Olinda, 296 - Recife Antigo, Recife, PE");
        c.setTelefone("(81) 3037-3240");
        c.setCep("50030-000");
        adicionaCartorioBanco(c);
    }

    public Cartorio adicionaCartorioBanco(Cartorio elemento) {

        String sql = "insert into cartorio "
                + "(nome_cartorio, endereco_cartorio, telefone_cartorio, cep_cartorio)"
                + " values (?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getEndereco());            
            stmt.setString(3, elemento.getTelefone());            
            stmt.setString(4, elemento.getCEP());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Cartorio atualizaCartorioBanco(Cartorio elemento) {

        String sql = "update cartorio set nome_cartorio = ?, endereco_cartorio = ?, telefone_cartorio = ?, cep_cartorio = ?, where id_cartorio = ?";

        Long id = (long) 1;

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getEndereco());            
            stmt.setString(3, elemento.getTelefone());            
            stmt.setString(4, elemento.getCEP());
            stmt.setLong(5, id);
            

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Cartorio retornaCartorioBanco() {

        String sql = "select * from cartorio where id_cartorio = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            
            Long id = (long) 1;
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cartorio elemento = new Cartorio();
                    elemento.setNome(rs.getString("nome_cartorio"));
                    elemento.setEndereco(rs.getString("endereco_cartorio"));
                    elemento.setTelefone(rs.getString("telefone_cartorio"));
                    elemento.setCep(rs.getString("cep_cartorio"));

                    return elemento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    public boolean atualizaCartorio(String nomeAtt, String enderecoAtt, String telefoneAtt, String cepAtt) {
        Cartorio cartorio = retornaCartorioBanco();

        if (!nomeAtt.equals("")) {
            cartorio.setNome(nomeAtt);
        }
        if (!enderecoAtt.equals("")) {
            cartorio.setEndereco(enderecoAtt);
        }
        if (!telefoneAtt.equals("")) {
            cartorio.setTelefone(telefoneAtt);
        }
        if (!cepAtt.equals("")) {
            cartorio.setCep(cepAtt);
        }

        atualizaCartorioBanco(cartorio);
        return true;
    }

    public Cartorio retornaCartorio() {
        return retornaCartorioBanco();
    }
}
