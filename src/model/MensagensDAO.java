package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MensagensDAO {

    public MensagensDAO(LocalDateTime dataInicial) {
    }

    public Mensagens adicionaMensagemBanco(Mensagens elemento) {

        String sql = "insert into mensagens "
                + "(mensagem, nome_mensageiro, dataCriacao)"
                + " values (?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getMensagem());
            stmt.setString(2, elemento.getNomeDoMensageiro());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(elemento.getDataCriacao()));

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Mensagens atualizaMensagemBanco(Mensagens elemento) {

        String sql = "update mensagens set mensagem = ?, nome_mensageiro = ?, dataModificacao = ? where id_mensagem = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getMensagem());
            stmt.setString(2, elemento.getNomeDoMensageiro());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(elemento.getDataModificacao()));
            stmt.setLong(4, elemento.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public boolean excluindoMensagemBanco(Long id) {

        String sql = "delete from mensagens where id_mensagem = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Mensagens> buscarTodasAsMensagens() {

        List<Mensagens> listaMensagens = new ArrayList<>();
        String sql = "select * from mensagens";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mensagens elemento = new Mensagens();
                elemento.setId(rs.getLong("id_mensagem"));
                elemento.setMensagem(rs.getString("mensagem"));
                elemento.setNomeDoMensageiro(rs.getString("nome_mensageiro"));

                String dataCriacao = rs.getString("dataCriacao");
                if (dataCriacao != null) {
                    elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                }

                String dataModificacao = rs.getString("dataModificacao");
                if (dataModificacao != null) {
                    elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                }

                listaMensagens.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar mensagens: " + e.getMessage(), e);
        }

        return listaMensagens;
    }

    public Mensagens buscarMensagemByIdBanco(Long id) {

        String sql = "select * from mensagens where id_mensagem = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Mensagens elemento = new Mensagens();
                    elemento.setId(rs.getLong("id_mensagem"));
                    elemento.setMensagem(rs.getString("mensagem"));
                    elemento.setNomeDoMensageiro(rs.getString("nome_mensageiro"));

                    String dataCriacao = rs.getString("dataCriacao");
                    if (dataCriacao != null) {
                        elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                    }

                    String dataModificacao = rs.getString("dataModificacao");
                    if (dataModificacao != null) {
                        elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                    }

                    return elemento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    public boolean criaMensagem(String nomeEnviado, String mensagemEnviada, LocalDateTime calendario) {
        Mensagens mensagem = new Mensagens();
        mensagem.setNomeDoMensageiro(nomeEnviado);
        mensagem.setMensagem(mensagemEnviada);
        mensagem.setDataCriacao(calendario);

        adicionaMensagemBanco(mensagem);
        return true;
    }

    public boolean atualizaMensagem(String nomeDoMensageiro, String mensagemAtt, int id, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Mensagens mensagem = buscarMensagemByIdBanco(idLong);

        if (mensagem != null && mensagem.getId() == id) {
            if (!nomeDoMensageiro.equals("")) {
                mensagem.setNomeDoMensageiro(nomeDoMensageiro);
            }
            if (!mensagem.equals("")) {
                mensagem.setMensagem(mensagemAtt);
            }
            mensagem.setDataModificacao(calendario);

            atualizaMensagemBanco(mensagem);
            return true;
        }
        return false;
    }

    public void excluiMensagem(int id) {
        Long idLong = Long.valueOf(id);
        excluindoMensagemBanco(idLong);
    }

    public String verMensagens() {
        StringBuilder m = new StringBuilder();
        List<Mensagens> mensagens = buscarTodasAsMensagens();

        for (Mensagens mensagem : mensagens) {
            if (mensagem != null) {
                m.append(mensagem.toString()).append("\n");
            }
        }
        return m.toString();
    }

    public Mensagens retornaMensagemByID(int id) {
        Long idLong = Long.valueOf(id);
        return buscarMensagemByIdBanco(idLong);
    }
}
