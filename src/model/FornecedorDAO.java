package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public FornecedorDAO(/*PagamentoDAO pagamentodao,*/LocalDateTime calendario) {
    }

    public Fornecedor adicionaFornecedorBanco(Fornecedor elemento) {

        String sql = "insert into fornecedor "
                + "(nome,cnpj,telefone,valorAPagar,parcelas,estado_fornecedor,dataCriacao)"
                + " values (?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getCnpj());
            stmt.setString(3, elemento.getTelefone());
            stmt.setDouble(4, elemento.getValorAPagar());
            stmt.setInt(5, elemento.getParcelas());
            stmt.setBoolean(6, elemento.getEstado());
            stmt.setString(7, (elemento.getDataCriacao()));

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Fornecedor atualizaFornecedorBanco(Fornecedor elemento) {

        String sql = "update fornecedor set nome = ?, cnpj = ?, telefone = ?, valorAPagar = ?, parcelas = ?, estado_fornecedor = ?, dataModificacao = ? where id_fornecedor = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getCnpj());
            stmt.setString(3, elemento.getTelefone());
            stmt.setDouble(4, elemento.getValorAPagar());
            stmt.setInt(5, elemento.getParcelas());
            stmt.setBoolean(6, elemento.getEstado());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(elemento.getDataCriacao()));
            stmt.setLong(8, elemento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public boolean excluindoFornecedorBanco(Long id) {

        String sql = "delete from fornecedor where id_fornecedor = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    public List<Fornecedor> buscarTodosOsFornecedores() {

        List<Fornecedor> listaFornecedores = new ArrayList<>();
        String sql = "select * from fornecedor";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Fornecedor elemento = new Fornecedor();
                elemento.setId(rs.getLong("id_fornecedor"));
                elemento.setNome(rs.getString("nome"));
                elemento.setCnpj(rs.getString("cnpj"));
                elemento.setTelefone(rs.getString("telefone"));
                elemento.setValorAPagar(rs.getDouble("valorAPagar"));
                elemento.setParcelas(rs.getInt("parcelas"));
                elemento.setEstado(rs.getBoolean("estado_fornecedor"));

                String dataCriacao = rs.getString("dataCriacao");
                if (dataCriacao != null) {
                    elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                }

                String dataModificacao = rs.getString("dataModificacao");
                if (dataModificacao != null) {
                    elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                }

                listaFornecedores.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedores: " + e.getMessage(), e);
        }

        return listaFornecedores;
    }

    public Fornecedor buscarFornecedorByIdBanco(Long id) {

        String sql = "select * from fornecedor where id_fornecedor = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Fornecedor elemento = new Fornecedor();
                    elemento.setId(rs.getLong("id_fornecedor"));
                    elemento.setNome(rs.getString("nome"));
                    elemento.setCnpj(rs.getString("cnpj"));
                    elemento.setTelefone(rs.getString("telefone"));
                    elemento.setValorAPagar(rs.getDouble("valorAPagar"));
                    elemento.setParcelas(rs.getInt("parcelas"));
                    elemento.setEstado(rs.getBoolean("estado_fornecedor"));

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

    public double calculaParcela(int id) {
        Long idLong = Long.valueOf(id);
        double valorDaParcela = buscarFornecedorByIdBanco(idLong).getValorAPagar() / buscarFornecedorByIdBanco(idLong).getParcelas();
        return valorDaParcela;
    }

    public Fornecedor registraFornecedor(String nome, String cnpj, String telefone, double valorAPagar, int parcelas, LocalDateTime calendario) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setCnpj(cnpj);
        fornecedor.setTelefone(telefone);
        fornecedor.setValorAPagar(valorAPagar);
        fornecedor.setParcelas(parcelas);
        fornecedor.setDataCriacao(calendario);

        adicionaFornecedorBanco(fornecedor);
        return fornecedor;
    }

    public boolean atualizaFornecedor(String nome, String cnpj, String telefone, double valorAPagar, int parcelas, int id, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Fornecedor fornecedor = buscarFornecedorByIdBanco(idLong);

        if (fornecedor != null && fornecedor.getId() == id) {
            if (!nome.equals("")) {
                fornecedor.setNome(nome);
            }
            if (!cnpj.equals("")) {
                fornecedor.setCnpj(cnpj);
            }
            if (!telefone.equals("")) {
                fornecedor.setTelefone(telefone);
            }
            fornecedor.setValorAPagar(valorAPagar);
            fornecedor.setParcelas(parcelas);
            fornecedor.setDataModificacao(calendario);

            atualizaFornecedorBanco(fornecedor);
            return true;
        }
        return false;
    }

    public void excluirFornecedor(int id) {
        Long idLong = Long.valueOf(id);
        excluindoFornecedorBanco(idLong);
    }
    
    public void excluirFornecedor(int id, ParcelaDAO parcelaDAO, PagamentoDAO pagamentoDAO, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {    
        List<Pagamento> pagamentosRelacionados = pagamentoDAO.buscarPagamentosPorFornecedor(id, fornecedordao);

        for (Pagamento pagamento : pagamentosRelacionados) {
            parcelaDAO.excluindoParcelaBanco(pagamento.getId());
        }        
        Long idLong = Long.valueOf(id);
        pagamentoDAO.excluindoPagamentoBanco(idLong);
    }

    public boolean pagarFornecedor(int id, boolean registro, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Fornecedor fornecedor = buscarFornecedorByIdBanco(idLong);
        if (registro != false) {
            fornecedor.setEstado(registro);
            fornecedor.setDataModificacao(calendario);

            atualizaFornecedorBanco(fornecedor);
            return true;
        }
        return false;
    }

    public String verFornecedores() {
        StringBuilder m = new StringBuilder();
        List<Fornecedor> fornecedores = buscarTodosOsFornecedores();

        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor != null) {
                m.append(fornecedor.toString()).append("\n");
            }
        }
        return m.toString();
    }

    public Fornecedor recebeFornecedorByID(int id) {
        Long idLong = Long.valueOf(id);
        return buscarFornecedorByIdBanco(idLong);
    }
}
