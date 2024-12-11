package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CerimonialDAO {

    public CerimonialDAO(LocalDateTime calendario) {
    }

    public Cerimonial adicionaCerimonialBanco(Cerimonial cerimonial) {

        String sql = "insert into cerimonial "
                + "(nome, funcao, dataCriacao)"
                + " values (?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cerimonial.getNome());
            stmt.setString(2, cerimonial.getFuncao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(cerimonial.getDataCriacao()));

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return cerimonial;
    }

    public Cerimonial atualizaCerimonialBanco(Cerimonial cerimonial) {

        String sql = "update mensagens set cerimonial = ?, nome_mensageiro = ?, dataModificacao = ? where id_cerimonial = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cerimonial.getNome());
            stmt.setString(2, cerimonial.getFuncao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(cerimonial.getDataModificacao()));
            stmt.setLong(4, cerimonial.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return cerimonial;
    }

    public boolean excluindoCerimonialBanco(Long id) {

        String sql = "delete from cerimonial where id_cerimonial = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Cerimonial> buscarTodosOsCerimoniais() {

        List<Cerimonial> listaCerimoniais = new ArrayList<>();
        String sql = "select * from Cerimonial";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cerimonial elemento = new Cerimonial();
                elemento.setId(rs.getLong("id_cerimonial"));
                elemento.setNome(rs.getString("nome"));
                elemento.setFuncao(rs.getString("funcao"));

                java.sql.Timestamp dataCriacao = rs.getTimestamp("dataCriacao");
                if (dataCriacao != null) {
                    elemento.setDataCriacao(dataCriacao.toLocalDateTime());
                }

                java.sql.Timestamp dataModificacao = rs.getTimestamp("dataModificacao");
                if (dataModificacao != null) {
                    elemento.setDataModificacao(dataModificacao.toLocalDateTime());
                }

                listaCerimoniais.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoas: " + e.getMessage(), e);
        }

        return listaCerimoniais;
    }

    public Cerimonial buscarCerimonialByIdBanco(Long id) {

        String sql = "select * from Cerimonial where id_cerimonial = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cerimonial elemento = new Cerimonial();
                    elemento.setId(rs.getLong("id_cerimonial"));
                    elemento.setNome(rs.getString("nome"));
                    elemento.setFuncao(rs.getString("funcao"));

                    java.sql.Timestamp dataCriacao = rs.getTimestamp("dataCriacao");
                    if (dataCriacao != null) {
                        elemento.setDataCriacao(dataCriacao.toLocalDateTime());
                    }

                    java.sql.Timestamp dataModificacao = rs.getTimestamp("dataModificacao");
                    if (dataModificacao != null) {
                        elemento.setDataModificacao(dataModificacao.toLocalDateTime());
                    }

                    return elemento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return null;
    }

    public Cerimonial criarCerimonial(String nome, String funcao, LocalDateTime calendario) {
        Cerimonial c = new Cerimonial();
        c.setNome(nome);
        c.setFuncao(funcao);
        c.setDataCriacao(calendario);

        adicionaCerimonialBanco(c);
        return c;
    }

    public boolean atualizaCerimonial(int id, String nomeAtt, String funcaoAtt, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Cerimonial cerimonial = buscarCerimonialByIdBanco(idLong);

        if (cerimonial != null && cerimonial.getId() == id) {
            if (!nomeAtt.equals("")) {
                cerimonial.setNome(nomeAtt);
            }
            if (!funcaoAtt.equals("")) {
                cerimonial.setFuncao(funcaoAtt);
            }
            cerimonial.setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public void excluirCerimonial(int id) {
        Long idLong = Long.valueOf(id);
        excluindoCerimonialBanco(idLong);
    }

    public String verCerimoniaisAdmin() {
        StringBuilder m = new StringBuilder();
        List<Cerimonial> cerimoniais = buscarTodosOsCerimoniais();

        for (Cerimonial cerimonial : cerimoniais) {
            if (cerimonial != null) {
                m.append(cerimonial.toStringAdmin()).append("\n");
            }
        }
        return m.toString();
    }

    public String verCerimoniais() {
        StringBuilder m = new StringBuilder();
        List<Cerimonial> cerimoniais = buscarTodosOsCerimoniais();

        for (Cerimonial cerimonial : cerimoniais) {
            if (cerimonial != null) {
                m.append(cerimonial.toString());
            }
        }
        return m.toString();
    }

    public Cerimonial retornaCerimonialByID(int id) {
        Long idLong = Long.valueOf(id);
        return buscarCerimonialByIdBanco(idLong);
    }
}
