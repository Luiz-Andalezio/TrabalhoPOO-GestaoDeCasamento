package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public UsuarioDAO(PessoaDAO pessoadao, LocalDateTime calendario) {
    }

    public Usuario adicionaUsuarioBanco(Usuario elemento) {

        String sql = "insert into usuario "
                + "(id_pessoa_fk,tipo,login,senha,dataCriacao)"
                + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, elemento.getPessoa().getId());
            stmt.setString(2, elemento.getTipo());
            stmt.setString(3, elemento.getLogin());
            stmt.setString(4, elemento.getSenha());
            stmt.setString(5, elemento.getDataCriacao());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Usuario atualizaUsuarioBanco(Usuario elemento) {

        String sql = "update usuario set id_pessoa_fk = ?, tipo = ?, login = ?, senha = ?, dataModificacao = ? where id_usuario = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, elemento.getPessoa().getId());
            stmt.setString(2, elemento.getTipo());
            stmt.setString(3, elemento.getLogin());
            stmt.setString(4, elemento.getSenha());
            stmt.setString(5, elemento.getDataModificacao());
            stmt.setLong(6, elemento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public boolean excluindoUsuarioBanco(Long id) {

        String sql = "delete from usuario where id_usuario = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    public List<Usuario> buscarTodosOsUsuarios(PessoaDAO pessoadao, LocalDateTime calendario) {

        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "select * from usuario";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Long id_pessoa_fk = rs.getLong("id_pessoa_fk");

                Usuario elemento = new Usuario();
                elemento.setId(rs.getLong("id_usuario"));
                elemento.setPessoa(pessoadao.buscarPessoaByIdBanco(id_pessoa_fk));
                elemento.setTipo(rs.getString("tipo"));
                elemento.setLogin(rs.getString("login"));
                elemento.setSenha(rs.getString("senha"));

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

                listaUsuarios.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuarios: " + e.getMessage(), e);
        }

        return listaUsuarios;
    }

    public Usuario buscarUsuarioByIdBanco(Long id, PessoaDAO pessoadao, LocalDateTime calendario) {

        String sql = "select * from usuario where id_usuario = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Long id_pessoa_fk = rs.getLong("id_pessoa_fk");

                    Usuario elemento = new Usuario();
                    elemento.setId(rs.getLong("id_usuario"));
                    elemento.setPessoa(pessoadao.buscarPessoaByIdBanco(id_pessoa_fk));
                    elemento.setTipo(rs.getString("tipo"));
                    elemento.setLogin(rs.getString("login"));
                    elemento.setSenha(rs.getString("senha"));

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

                    return elemento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return null;
    }

    public Usuario recebePessoa(LocalDateTime calendario, Pessoa novaPessoa, String tipo, String login, String senha, PessoaDAO pessoadao) {
        Usuario usuario = new Usuario();
        usuario.setPessoa(novaPessoa);
        usuario.setTipo(tipo);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setDataCriacao(calendario);

        adicionaUsuarioBanco(usuario);
        return usuario;
    }

    public boolean atualizaPessoaUsuario(LocalDateTime calendario, String nomeAtt, String telefoneAtt, String nascimentoAtt, int id, PessoaDAO pessoadao) {
        Long idLong = Long.valueOf(id);
        Pessoa pessoa = buscarUsuarioByIdBanco(idLong, pessoadao, calendario).getPessoa();

        if (pessoa != null && pessoa.getId() == buscarUsuarioByIdBanco(idLong, pessoadao, calendario).getPessoa().getId()) {
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

            pessoadao.atualizaPessoaBanco(pessoa);
            return true;
        }
        return false;
    }

    public boolean atualizaUsuario(LocalDateTime calendario, String tipo, String login, String senha, int id, PessoaDAO pessoadao) {
        Long idLong = Long.valueOf(id);
        Usuario usuario = buscarUsuarioByIdBanco(idLong, pessoadao, calendario);

        if (usuario != null && usuario.getId() == id) {
            if (!tipo.equals("")) {
                usuario.setTipo(tipo);
            }
            if (!login.equals("")) {
                usuario.setLogin(login);
            }
            if (!senha.equals("")) {
                usuario.setSenha(senha);
            }
            usuario.setDataModificacao(calendario);

            atualizaUsuarioBanco(usuario);
            return true;
        }
        return false;
    }

    public void excluirUsuario(int id) {
        Long idLong = Long.valueOf(id);
        excluindoUsuarioBanco(idLong);
    }

    public String verUsuarios(PessoaDAO pessoadao, LocalDateTime calendario) {
        StringBuilder m = new StringBuilder();
        List<Usuario> usuarios = buscarTodosOsUsuarios(pessoadao, calendario);

        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                m.append(usuario.toString()).append("\n");
            }
        }
        return m.toString();
    }

    public Usuario retornaUsuarioByID(int id, PessoaDAO pessoadao, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Usuario usuario = buscarUsuarioByIdBanco(idLong, pessoadao, calendario);
        return usuario;
    }

    public Usuario retornaUsuarioByLoginESenha(String login, String senha, PessoaDAO pessoadao) {

        String sql = "select * from usuario where login = ? and senha = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Long id_pessoa_fk = rs.getLong("id_pessoa_fk");

                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id_usuario"));
                    usuario.setPessoa(pessoadao.buscarPessoaByIdBanco(id_pessoa_fk));
                    usuario.setTipo(rs.getString("tipo"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));

                    java.sql.Timestamp dataCriacao = rs.getTimestamp("dataCriacao");
                    if (dataCriacao != null) {
                        usuario.setDataCriacao(dataCriacao.toLocalDateTime());
                    }

                    java.sql.Timestamp dataModificacao = rs.getTimestamp("dataModificacao");
                    if (dataModificacao != null) {
                        usuario.setDataModificacao(dataModificacao.toLocalDateTime());
                    }

                    return usuario;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por login e senha", e);
        }

        return null;
    }
}
