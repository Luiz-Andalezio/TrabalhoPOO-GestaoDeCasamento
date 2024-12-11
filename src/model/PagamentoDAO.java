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

public class PagamentoDAO {

    public PagamentoDAO(PessoaDAO pessoadao, FornecedorDAO fornecedordao, ParcelaDAO parceladao, LocalDateTime calendario) {
    }

    public Pagamento adicionaPagamentoBanco(Pagamento elemento) {

        String sql = "insert into pagamento "
                + "(descricao,id_fornecedor_fk,dataCriacao)"
                + " values (?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, elemento.getDescricao());
            if (elemento.getFornecedor() != null) {
                stmt.setLong(2, elemento.getFornecedor().getId());
            }
            stmt.setString(3, elemento.getDataCriacao());

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

    public Pagamento atualizaPagamentoBanco(Pagamento elemento) {

        String sql = "update pagamento set descricao = ?, id_fornecedor_fk = ?, dataModificacao = ? where id_pagamento = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getDescricao());
            if (elemento.getFornecedor() != null) {
                stmt.setLong(2, elemento.getFornecedor().getId());
            }
            stmt.setString(3, elemento.getDataModificacao());
            stmt.setLong(4, elemento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public boolean excluindoPagamentoBanco(Long id) {

        String sql = "delete from pagamento where id_pagamento = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    public List<Pagamento> buscarTodosOsPagamentos(FornecedorDAO fornecedordao) {

        List<Pagamento> listaPagamentos = new ArrayList<>();
        String sql = "select * from pagamento";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagamento elemento = new Pagamento();
                elemento.setId(rs.getLong("id_pagamento"));
                elemento.setDescricao(rs.getString("descricao"));
                elemento.setFornecedor(fornecedordao.buscarFornecedorByIdBanco(rs.getLong("id_fornecedor_fk")));

                String dataCriacao = rs.getString("dataCriacao");
                if (dataCriacao != null) {
                    elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                }

                String dataModificacao = rs.getString("dataModificacao");
                if (dataModificacao != null) {
                    elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                }

                listaPagamentos.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamentos: " + e.getMessage(), e);
        }

        return listaPagamentos;
    }

    public Pagamento buscarPagamentoByIdBanco(Long id, FornecedorDAO fornecedordao) {

        String sql = "select * from pagamento where id_pagamento = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pagamento elemento = new Pagamento();
                    elemento.setId(rs.getLong("id_pagamento"));
                    elemento.setDescricao(rs.getString("descricao"));
                    if (elemento.getFornecedor() != null) {
                        elemento.setFornecedor(fornecedordao.buscarFornecedorByIdBanco(rs.getLong("id_fornecedor_fk")));
                    }

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

    public List<Pagamento> buscarPagamentosPorFornecedor(int idPagamento, FornecedorDAO fornecedordao) {
        List<Pagamento> listaPagamentosDoFornecedor = new ArrayList<>();
        Long idLong = Long.valueOf(idPagamento);

        String sql = "select * from pagamento where id_fornecedor_fk = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idLong);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pagamento elemento = new Pagamento();
                    elemento.setId(rs.getLong("id_pagamento"));
                    elemento.setDescricao(rs.getString("descricao"));
                    if (elemento.getFornecedor() != null) {
                        elemento.setFornecedor(fornecedordao.buscarFornecedorByIdBanco(rs.getLong("id_fornecedor_fk")));
                    }

                    String dataCriacao = rs.getString("dataCriacao");
                    if (dataCriacao != null) {
                        elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                    }

                    String dataModificacao = rs.getString("dataModificacao");
                    if (dataModificacao != null) {
                        elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                    }

                    listaPagamentosDoFornecedor.add(elemento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return listaPagamentosDoFornecedor;
    }

    public int contarParcelasPorPagamento(int idPagamento, FornecedorDAO fornecedordao) {
        int quantidadeDeParcelas = 0;
        Long idLong = Long.valueOf(idPagamento);

        String sql = "select * from pagamento where id_fornecedor_fk = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idLong);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    /*
                    Pagamento elemento = new Pagamento();
                    elemento.setId(rs.getLong("id_pessoa"));
                    elemento.setDescricao(rs.getString("descricao"));
                    if (elemento.getFornecedor() != null) {
                        elemento.setFornecedor(fornecedordao.buscarFornecedorByIdBanco(rs.getLong("id_fornecedor_fk")));
                    }

                    String dataCriacao = rs.getString("dataCriacao");
                    if (dataCriacao != null) {
                        elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                    }

                    String dataModificacao = rs.getString("dataModificacao");
                    if (dataModificacao != null) {
                        elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                    }
                     */

                    quantidadeDeParcelas++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return quantidadeDeParcelas;
    }

    public Pagamento registraPagamento(Fornecedor fornecedor, String descricao, LocalDateTime calendario) {
        Pagamento pagamento = new Pagamento();
        pagamento.setFornecedor(fornecedor);
        pagamento.setDescricao(descricao);
        pagamento.setDataCriacao(calendario);

        return adicionaPagamentoBanco(pagamento);
    }

    public Pagamento registraDescricaoPagamento(String descricao, LocalDateTime calendario) {
        Pagamento pagamento = new Pagamento();
        pagamento.setDescricao(descricao);
        pagamento.setDataCriacao(calendario);

        return adicionaPagamentoBanco(pagamento);
    }

    public boolean atualizaFornecedorByPagamento(int id, Fornecedor fornecedor, FornecedorDAO fornecedordao, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Pagamento pagamento = buscarPagamentoByIdBanco(idLong, fornecedordao);
        pagamento.setFornecedor(fornecedor);
        pagamento.setDataModificacao(calendario);

        atualizaPagamentoBanco(pagamento);
        return true;
    }

    public boolean atualizaDescricao(int id, String descricao, FornecedorDAO fornecedordao, LocalDateTime calendario) {
        Long idLong = Long.valueOf(id);
        Pagamento pagamento = buscarPagamentoByIdBanco(idLong, fornecedordao);
        pagamento.setDescricao(descricao);
        pagamento.setDataModificacao(calendario);

        atualizaPagamentoBanco(pagamento);
        return true;
    }

    public void excluirPagamento(int id, ParcelaDAO parcelaDAO, PagamentoDAO pagamentoDAO, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {

        List<Parcela> parcelasRelacionadas = parcelaDAO.buscarParcelasPorPagamento(id, pagamentoDAO, fornecedordao, pessoadao);

        for (Parcela parcela : parcelasRelacionadas) {
            parcelaDAO.excluindoParcelaBanco(parcela.getId());
        }
        Long idLong = Long.valueOf(id);

        pagamentoDAO.excluindoPagamentoBanco(idLong);
    }

    public String verPagamentos(PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, ParcelaDAO parceladao, PessoaDAO pessoadao) {
        StringBuilder m = new StringBuilder();
        List<Pagamento> pagamentos = buscarTodosOsPagamentos(fornecedordao);

        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null) {
                m.append(pagamento.toStringParameter(pagamento, pagamentodao, fornecedordao, parceladao, pessoadao));
            }
        }
        return m.toString();
    }

    public Pagamento retornaPagamentoByID(int id, FornecedorDAO fornecedordao) {
        Long idLong = Long.valueOf(id);
        return buscarPagamentoByIdBanco(idLong, fornecedordao);
    }

    public Pagamento retornaPagamentoByPagamento(Pagamento pagamento, FornecedorDAO fornecedordao) {

        String sql = "select * from pagamento where id_pagamento = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, pagamento.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pagamento elemento = new Pagamento();
                    elemento.setId(rs.getLong("id_pagamento"));
                    elemento.setDescricao(rs.getString("descricao"));
                    if (elemento.getFornecedor() != null) {
                        elemento.setFornecedor(fornecedordao.buscarFornecedorByIdBanco(rs.getLong("id_fornecedor_fk")));
                    }

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
}
