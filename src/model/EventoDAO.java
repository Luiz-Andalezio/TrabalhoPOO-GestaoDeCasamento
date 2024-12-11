package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EventoDAO {

    public EventoDAO(PessoaDAO pessoadao, IgrejaDAO igrejadao, CartorioDAO cartoriodao, LocalDateTime calendario) {
    }  

    public Evento adicionaEventoBanco(Evento elemento) {

        String sql = "insert into evento "
                + "(data_evento,igreja_evento_fk,cartorio_evento_fk,pessoa_noivo_fk,pessoa_noiva_fk,dataCriacao)"
                + " values (?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getData());
            stmt.setLong(1, 1);
            stmt.setLong(1, 1);
            stmt.setLong(1, 1);
            stmt.setLong(1, 2);
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(elemento.getDataCriacao()));

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Evento atualizaEventoBanco(Evento elemento) {

        String sql = "update evento set data_evento, igreja_evento_fk = ?, cartorio_evento_fk = ?, pessoa_noivo_fk = ?, pessoa_noiva = ?, dataModificacao = ? where id_evento = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getData());
            stmt.setLong(1, 1);
            stmt.setLong(1, 1);
            stmt.setLong(1, 1);
            stmt.setLong(1, 2);
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(elemento.getDataModificacao()));
            stmt.setLong(6, elemento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Evento buscarEventoBanco(IgrejaDAO igrejadao, CartorioDAO cartoriodao, PessoaDAO pessoadao) {

        String sql = "select * from evento where id_evento = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            Long id = (long) 1;

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Long id_noivo = rs.getLong("pessoa_noivo_fk");
                    Long id_noiva = rs.getLong("pessoa_noiva_fk");

                    Evento elemento = new Evento();
                    elemento.setId(rs.getLong("id_evento"));
                    elemento.setData(rs.getString("data_evento"));
                    elemento.setIgreja(igrejadao.retornaIgrejaBanco());
                    elemento.setCartorio(cartoriodao.retornaCartorioBanco());
                    elemento.setPessoaNoivo(pessoadao.buscarPessoaByIdBanco(id_noivo));
                    elemento.setPessoaNoiva(pessoadao.buscarPessoaByIdBanco(id_noiva));

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
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }
        
    public boolean atualizaNomeNoivo(String novoNomeNoivo, IgrejaDAO igrejadao, CartorioDAO cartoriodao, PessoaDAO pessoadao, LocalDateTime calendario) {
        Pessoa pessoa = buscarEventoBanco(igrejadao, cartoriodao, pessoadao).getPessoaNoivo();

        if (pessoa != null && pessoa.getId() == buscarEventoBanco(igrejadao, cartoriodao, pessoadao).getPessoaNoivo().getId()) {
            if (!novoNomeNoivo.equals("")) {
                pessoa.setNome(novoNomeNoivo);
            }
            pessoa.setDataModificacao(calendario);

            pessoadao.atualizaPessoaBanco(pessoa);
            return true;
        }
        return false;
    }

    public boolean atualizaNomeNoiva(String novoNomeNoiva, IgrejaDAO igrejadao, CartorioDAO cartoriodao, PessoaDAO pessoadao, LocalDateTime calendario) {
        Pessoa pessoa = buscarEventoBanco(igrejadao, cartoriodao, pessoadao).getPessoaNoiva();

        if (pessoa != null && pessoa.getId() == buscarEventoBanco(igrejadao, cartoriodao, pessoadao).getPessoaNoiva().getId()) {
            if (!novoNomeNoiva.equals("")) {
                pessoa.setNome(novoNomeNoiva);
            }
            pessoa.setDataModificacao(calendario);

            pessoadao.atualizaPessoaBanco(pessoa);
            return true;
        }
        return false;
    }

    public boolean atualizaDataEvento(String dataAtt, IgrejaDAO igrejadao, CartorioDAO cartoriodao, PessoaDAO pessoadao, LocalDateTime calendario) {
        Evento evento = buscarEventoBanco(igrejadao, cartoriodao, pessoadao);
        evento.setData(dataAtt);
        evento.setDataModificacao(calendario);

        atualizaEventoBanco(evento);
        return true;
    }

    public Evento retornaEvento(IgrejaDAO igrejadao, CartorioDAO cartoriodao, PessoaDAO pessoadao){
        return buscarEventoBanco(igrejadao, cartoriodao, pessoadao);
    }
}
