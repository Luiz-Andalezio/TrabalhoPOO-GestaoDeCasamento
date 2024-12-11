package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IgrejaDAO {

    public IgrejaDAO() {
        Igreja i = new Igreja();
        i.setNome("Igreja Madre de Deus");
        i.setEndereco("Rua Madre de Deus, Recife, PE");
        i.setCEP("50030-110");
        adicionaIgrejaBanco(i);
    }

    public Igreja adicionaIgrejaBanco(Igreja elemento) {

        String sql = "insert into igreja "
                + "(nome, endereco, CEP)"
                + " values (?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getEndereco());             
            stmt.setString(3, elemento.getCEP());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Igreja atualizaIgrejaBanco(Igreja elemento) {

        String sql = "update igreja set nome = ?, endereco = ?, telefone = ?, CEP = ?, where id_igreja = ?";

        Long id = (long) 1;

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getEndereco());            
            stmt.setString(3, elemento.getCEP());
            stmt.setLong(4, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Igreja retornaIgrejaBanco() {

        String sql = "select * from igreja where id_igreja = ?";

        Long id = (long) 1;

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Igreja elemento = new Igreja();
                    elemento.setNome(rs.getString("nome"));
                    elemento.setEndereco(rs.getString("endereco"));
                    elemento.setCEP(rs.getString("CEP"));

                    return elemento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    public boolean atualizaIgreja(String novoNome, String endereco, String CEP) {
        Igreja igreja = retornaIgrejaBanco();

        if (!novoNome.equals("")) {
            igreja.setNome(novoNome);
        }
        if (!endereco.equals("")) {
            igreja.setEndereco(endereco);
        }
        if (!CEP.equals("")) {
            igreja.setCEP(CEP);
        }

        atualizaIgrejaBanco(igreja);
        return true;
    }

    public Igreja retornaIgreja() {
        return retornaIgrejaBanco();
    }
}
