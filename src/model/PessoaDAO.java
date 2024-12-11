package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public PessoaDAO(LocalDateTime calendario) {
    }

    public Pessoa adicionaPessoaBanco(Pessoa elemento) {

        String sql = "insert into pessoa "
                + "(nome,nascimento,telefone,dataCriacao)"
                + " values (?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getNascimento());
            stmt.setString(3, elemento.getTelefone());
            stmt.setString(4, elemento.getDataCriacao());

            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    elemento.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Pessoa atualizaPessoaBanco(Pessoa elemento) {

        String sql = "update pessoa set nome = ?, nascimento = ?, telefone = ?, dataModificacao = ? where id_pessoa = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getNascimento());
            stmt.setString(3, elemento.getTelefone());
            stmt.setString(4, elemento.getDataModificacao());
            stmt.setLong(5, elemento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public boolean excluindoPessoaBanco(Pessoa elemento) {

        String sql = "delete from pessoa where id_pessoa = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, elemento.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    public List<Pessoa> buscarTodasAsPessoas(LocalDateTime calendario) {

        List<Pessoa> listaPessoas = new ArrayList<>();
        String sql = "select * from pessoa";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pessoa elemento = new Pessoa();
                elemento.setId(rs.getLong("id_pessoa"));
                elemento.setNome(rs.getString("nome"));
                elemento.setNascimento(rs.getString("nascimento"));
                elemento.setTelefone(rs.getString("telefone"));

                String dataCriacao = rs.getString("dataCriacao");
                if (dataCriacao != null) {
                    elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                } else {
                    elemento.setDataCriacao(calendario);
                }

                String dataModificacao = rs.getString("dataModificacao");
                if (dataModificacao != null) {
                    elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                }

                listaPessoas.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoas: " + e.getMessage(), e);
        }

        return listaPessoas;
    }

    public Pessoa buscarPessoaByIdBanco(Long id) {

        String sql = "select * from pessoa where id_pessoa = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pessoa elemento = new Pessoa();
                    elemento.setId(rs.getLong("id_pessoa"));
                    elemento.setNome(rs.getString("nome"));
                    elemento.setNascimento(rs.getString("nascimento"));
                    elemento.setTelefone(rs.getString("telefone"));

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

    public Pessoa criarPessoa(String nome, String telefone, String nascimento, LocalDateTime calendario) {
        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setTelefone(telefone);
        p.setNascimento(nascimento);
        p.setDataCriacao(calendario);

        return adicionaPessoaBanco(p);
    }

    public boolean atualizaPessoa(String nomeAtt, String telefoneAtt, String nascimentoAtt, int id, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Pessoa pessoa = buscarPessoaByIdBanco(idLong);

        if (pessoa != null && pessoa.getId() == id) {
            if (!nomeAtt.equals("")) {
                pessoa.setNome(nomeAtt);
            }
            if (!telefoneAtt.equals("")) {
                pessoa.setTelefone(telefoneAtt);
            }
            if (!nascimentoAtt.equals("")) {
                pessoa.setNascimento(nascimentoAtt);
            }
            pessoa.setDataModificacao(calendario);

            atualizaPessoaBanco(pessoa);
            return true;
        }
        return false;
    }

    public void excluirPessoaUsuarioBanco(Long idUsuario, Pessoa pessoa, UsuarioDAO usuariodao) {
        usuariodao.excluindoUsuarioBanco(idUsuario);
        excluindoPessoaBanco(pessoa);
    }

    /*
    public void excluirPessoaConviteIndividualBanco(Long idConvite, Pessoa pessoa, ConviteIndividualDAO conviteindividualdao) {
        conviteindividualdao.excluindoConviteIndividualBanco(idConvite);
        excluindoPessoaBanco(pessoa);
    }*/
    public String verPessoas(LocalDateTime calendario) {
        StringBuilder m = new StringBuilder();
        List<Pessoa> pessoas = buscarTodasAsPessoas(calendario);

        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                m.append(pessoa.toString()).append("\n");
            }
        }
        return m.toString();
    }
}
