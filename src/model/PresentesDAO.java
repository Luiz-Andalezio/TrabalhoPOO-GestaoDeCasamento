package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PresentesDAO {

    public PresentesDAO(LocalDateTime calendario) {
    }

    public Presentes adicionaPresenteBanco(Presentes elemento) {

        String sql = "insert into presentes "
                + "(nome_presente,tipo_presente,valor_presente,comprador_presente,dataCriacao)"
                + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getTipo());
            stmt.setDouble(3, elemento.getValor());
            stmt.setString(4, elemento.getNomeComprador());
            stmt.setString(4, elemento.getDataCriacao());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Presentes atualizaPresenteBanco(Presentes elemento) {

        String sql = "update presentes set nome_presente = ?, tipo_presente = ?, valor_presente = ?, comprador_presente = ?, dataModificacao = ? where id_presente = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getTipo());
            stmt.setDouble(3, elemento.getValor());
            stmt.setString(4, elemento.getNomeComprador());
            stmt.setString(4, elemento.getDataModificacao());
            stmt.setLong(6, elemento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public boolean excluindoPresenteBanco(Long id) {

        String sql = "delete from presentes where id_presente = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    public List<Presentes> buscarTodosOsPresentes() {

        List<Presentes> listaPresentes = new ArrayList<>();
        String sql = "select * from presentes";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Presentes elemento = new Presentes();
                elemento.setId(rs.getLong("id_presente"));
                elemento.setNome(rs.getString("nome_presente"));
                elemento.setTipo(rs.getString("tipo_presente"));
                elemento.setValor(rs.getDouble("valor_presente"));
                elemento.setNomeComprador(rs.getString("comprador_presente"));

                String dataCriacao = rs.getString("dataCriacao");
                if (dataCriacao != null) {
                    elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                }

                String dataModificacao = rs.getString("dataModificacao");
                if (dataModificacao != null) {
                    elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                }

                listaPresentes.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoas: " + e.getMessage(), e);
        }

        return listaPresentes;
    }

    public Presentes buscarPresenteByIdBanco(Long id_presente) {

        String sql = "select * from presentes where id_presente = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id_presente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Presentes elemento = new Presentes();
                    elemento.setId(rs.getLong("id_presente"));
                    elemento.setNome(rs.getString("nome_presente"));
                    elemento.setTipo(rs.getString("tipo_presente"));
                    elemento.setValor(rs.getDouble("valor_presente"));
                    elemento.setNomeComprador(rs.getString("comprador_presente"));

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
            throw new RuntimeException();
        }

        return null;
    }

    public Presentes registrarPresente(LocalDateTime calendario, String nome, String tipo, double valor) {
        Presentes p = new Presentes();
        p.setNome(nome);
        p.setTipo(tipo);
        p.setValor(valor);
        p.setDataCriacao(calendario);

        adicionaPresenteBanco(p);
        return p;
    }

    public boolean atualizaPresente(LocalDateTime calendario, String nomeAtt, String tipoAtt, double valorAtt, int id) {
        Long idLong = Long.valueOf(id);
        Presentes presente = buscarPresenteByIdBanco(idLong);

        if (presente != null && presente.getId() == id) {
            if (!nomeAtt.equals("")) {
                presente.setNome(nomeAtt);
            }
            if (!tipoAtt.equals("")) {
                presente.setTipo(tipoAtt);
            }
            presente.setValor(valorAtt);
            presente.setDataModificacao(calendario);

            atualizaPresenteBanco(presente);
            return true;
        }
        return false;
    }

    public void excluirPresente(int id) {
        Long idLong = Long.valueOf(id);
        excluindoPresenteBanco(idLong);
    }

    public boolean verificaPresente(int id) {
        Long idLong = Long.valueOf(id);
        return buscarPresenteByIdBanco(idLong).getNomeComprador() != null;
        /*
        if (buscarPresenteByIdBanco(idLong).getNomeComprador() != null) {
            return true;
        } else {
            return false;
        }
         */
    }

    public void compraPresente(LocalDateTime calendario, String nomeComprador, int id) {
        Long idLong = Long.valueOf(id);
        Presentes p = buscarPresenteByIdBanco(idLong);
        p.setNomeComprador(nomeComprador);
        p.setDataModificacao(calendario);

        atualizaPresenteBanco(p);
    }

    public String verPresentesComprados() {
        StringBuilder m = new StringBuilder();
        List<Presentes> presentes = buscarTodosOsPresentes();

        for (Presentes presente : presentes) {
            if (presente != null && presente.getNomeComprador() != null) {
                m.append(presentes.toString()).append("\n");
            }
        }
        return m.toString();
    }

    public String verPresentesCompradosAdmin(Usuario usuarioLogado) {
        StringBuilder m = new StringBuilder();
        List<Presentes> presentes = buscarTodosOsPresentes();

        for (Presentes presente : presentes) {
            if (presente != null && presente.getNomeComprador() != null) {
                m.append(presente.toString(usuarioLogado)).append("\n");
            }
        }
        return m.toString();
    }

    public String verPresentesAdmin(Usuario usuarioLogado) {
        StringBuilder m = new StringBuilder();
        List<Presentes> presentes = buscarTodosOsPresentes();

        for (Presentes presente : presentes) {
            if (presente != null) {
                m.append(presente.toString(usuarioLogado)).append("\n");
            }
        }
        return m.toString();
    }

    public String verPresentesConvidado() {
        StringBuilder m = new StringBuilder();
        List<Presentes> presentes = buscarTodosOsPresentes();

        for (Presentes presente : presentes) {
            if (presente != null) {
                m.append(presente.toString()).append("\n");
            }
        }
        return m.toString();
    }

    public Presentes retornaPresenteByID(int id) {
        Long idLong = Long.valueOf(id);
        return buscarPresenteByIdBanco(idLong);
    }
}
