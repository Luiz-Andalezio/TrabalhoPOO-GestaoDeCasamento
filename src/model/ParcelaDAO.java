package model;

import data_base_connector.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParcelaDAO {

    public ParcelaDAO(PessoaDAO pessoadao, FornecedorDAO fornecedordao, LocalDateTime calendario) {
    }

    public Parcela adicionaParcelaBanco(Parcela elemento) {

        String sql = "insert into parcela "
                + "(id_pagamento_fk,id_pessoa_pagante_fk,parcela,parcela_data,valor_parcela,estado_pagamento,dataCriacao)"
                + " values (?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, elemento.getPagamento().getId());
            stmt.setLong(2, elemento.getPagante().getId());
            stmt.setInt(3, elemento.getParcela());
            stmt.setString(4, elemento.getData());
            stmt.setDouble(5, elemento.getValorDaParcela());
            stmt.setBoolean(6, elemento.getEstadoPagamento());
            stmt.setString(7, elemento.getDataCriacao());

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

    public Parcela atualizaParcelaBanco(Parcela elemento) {

        String sql = "update parcela set id_pagamento_fk = ?, id_pessoa_pagante_fk = ?, parcela = ?, parcela_data = ?, valor_parcela = ?, estado_pagamento = ?, dataModificacao = ? where id_parcela = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, elemento.getPagamento().getId());
            stmt.setLong(2, elemento.getPagante().getId());
            stmt.setInt(3, elemento.getParcela());
            stmt.setString(4, elemento.getData());
            stmt.setDouble(5, elemento.getValorDaParcela());
            stmt.setBoolean(6, elemento.getEstadoPagamento());
            stmt.setString(7, elemento.getDataModificacao());
            stmt.setLong(8, elemento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public boolean excluindoParcelaBanco(Long id) {

        String sql = "delete from parcela where id_parcela = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    public List<Parcela> buscarTodasAsParcelas(PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {

        List<Parcela> listaParcelas = new ArrayList<>();
        String sql = "select * from parcela";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Parcela elemento = new Parcela();
                elemento.setId(rs.getLong("id_parcela"));
                elemento.setPagamento(pagamentodao.buscarPagamentoByIdBanco(rs.getLong("id_pagamento_fk"), fornecedordao));
                elemento.setPagante(pessoadao.buscarPessoaByIdBanco(rs.getLong("id_pessoa_pagante_fk")));
                elemento.setParcela(rs.getInt("parcela"));
                elemento.setData(rs.getString("parcela_data"));
                elemento.setValorDaParcela(rs.getDouble("valor_parcela"));
                elemento.setEstadoPagamento(rs.getBoolean("estado_pagamento"));

                String dataCriacao = rs.getString("dataCriacao");
                if (dataCriacao != null) {
                    elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                }

                String dataModificacao = rs.getString("dataModificacao");
                if (dataModificacao != null) {
                    elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                }

                listaParcelas.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamentos: " + e.getMessage(), e);
        }

        return listaParcelas;
    }

    public Parcela buscarParcelaByIdBanco(Long id, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {

        String sql = "select * from parcela where id_parcela = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Parcela elemento = new Parcela();
                    elemento.setId(rs.getLong("id_parcela"));
                    elemento.setPagamento(pagamentodao.buscarPagamentoByIdBanco(rs.getLong("id_pagamento_fk"), fornecedordao));
                    elemento.setPagante(pessoadao.buscarPessoaByIdBanco(rs.getLong("id_pessoa_pagante_fk")));
                    elemento.setParcela(rs.getInt("parcela"));
                    elemento.setData(rs.getString("parcela_data"));
                    elemento.setValorDaParcela(rs.getDouble("valor_parcela"));
                    elemento.setEstadoPagamento(rs.getBoolean("estado_pagamento"));

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

    public List<Parcela> buscarParcelasPorPagamento(int idPagamento, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {
        List<Parcela> listaParcelasDoPagamento = new ArrayList<>();

        Long idLong = Long.valueOf(idPagamento);

        String sql = "select * from parcela where id_pagamento = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idLong);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Parcela elemento = new Parcela();
                    elemento.setId(rs.getLong("id_parcela"));
                    elemento.setPagamento(pagamentodao.buscarPagamentoByIdBanco(rs.getLong("id_pagamento_fk"), fornecedordao));
                    elemento.setPagante(pessoadao.buscarPessoaByIdBanco(rs.getLong("id_pessoa_pagante_fk")));
                    elemento.setParcela(rs.getInt("parcela"));
                    elemento.setData(rs.getString("parcela_data"));
                    elemento.setValorDaParcela(rs.getDouble("valor_parcela"));
                    elemento.setEstadoPagamento(rs.getBoolean("estado_pagamento"));

                    String dataCriacao = rs.getString("dataCriacao");
                    if (dataCriacao != null) {
                        elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                    }

                    String dataModificacao = rs.getString("dataModificacao");
                    if (dataModificacao != null) {
                        elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                    }

                    listaParcelasDoPagamento.add(elemento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return listaParcelasDoPagamento;
    }

    public String buscarParcelasPorPagamentoToString(Long idPagamento, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {
        List<Parcela> listaParcelasDoPagamento = new ArrayList<>();

        String sql = "select * from parcela where id_pagamento_fk = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idPagamento);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Parcela elemento = new Parcela();
                    elemento.setId(rs.getLong("id_parcela"));
                    elemento.setPagamento(pagamentodao.buscarPagamentoByIdBanco(rs.getLong("id_pagamento_fk"), fornecedordao));
                    elemento.setPagante(pessoadao.buscarPessoaByIdBanco(rs.getLong("id_pessoa_pagante_fk")));
                    elemento.setParcela(rs.getInt("parcela"));
                    elemento.setData(rs.getString("parcela_data"));
                    elemento.setValorDaParcela(rs.getDouble("valor_parcela"));
                    elemento.setEstadoPagamento(rs.getBoolean("estado_pagamento"));
                    /*
                    String dataCriacao = rs.getString("dataCriacao");
                    if (dataCriacao != null) {
                        elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                    }

                    String dataModificacao = rs.getString("dataModificacao");
                    if (dataModificacao != null) {
                        elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                    }
                    */

                    listaParcelasDoPagamento.add(elemento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return listaParcelasDoPagamento.toString();
    }

    public List<Parcela> buscarParcelasPorPagamentoToList(Long idPagamento, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {
        List<Parcela> listaParcelasDoPagamento = new ArrayList<>();

        String sql = "select * from parcela where id_pagamento_fk = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idPagamento);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Parcela elemento = new Parcela();
                    elemento.setId(rs.getLong("id_parcela"));
                    elemento.setPagamento(pagamentodao.buscarPagamentoByIdBanco(rs.getLong("id_pagamento_fk"), fornecedordao));
                    elemento.setPagante(pessoadao.buscarPessoaByIdBanco(rs.getLong("id_pessoa_pagante_fk")));
                    elemento.setParcela(rs.getInt("parcela"));
                    elemento.setData(rs.getString("parcela_data"));
                    elemento.setValorDaParcela(rs.getDouble("valor_parcela"));
                    elemento.setEstadoPagamento(rs.getBoolean("estado_pagamento"));
                    /*
                    String dataCriacao = rs.getString("dataCriacao");
                    if (dataCriacao != null) {
                        elemento.setDataCriacaoByString(rs.getString("dataCriacao"));
                    }

                    String dataModificacao = rs.getString("dataModificacao");
                    if (dataModificacao != null) {
                        elemento.setDataModificacaoByString(rs.getString("dataModificacao"));
                    }
                    */

                    listaParcelasDoPagamento.add(elemento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return listaParcelasDoPagamento;
    }

    public Parcela buscarParcelaEspecifica(int idPagamento, int idParcela, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {
        Parcela parcelaEspecifica = null;
    
        Long idPagamentoLong = Long.valueOf(idPagamento);
        Long idParcelaLong = Long.valueOf(idParcela);
    
        String sql = "SELECT * FROM parcela WHERE id_pagamento_fk = ? AND parcela = ?";
    
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idPagamentoLong);
            stmt.setLong(2, idParcelaLong);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    parcelaEspecifica = new Parcela();
                    parcelaEspecifica.setId(rs.getLong("id_parcela"));
                    parcelaEspecifica.setPagamento(pagamentodao.buscarPagamentoByIdBanco(rs.getLong("id_pagamento_fk"), fornecedordao));
                    parcelaEspecifica.setPagante(pessoadao.buscarPessoaByIdBanco(rs.getLong("id_pessoa_pagante_fk")));
                    parcelaEspecifica.setParcela(rs.getInt("parcela"));
                    parcelaEspecifica.setData(rs.getString("parcela_data"));
                    parcelaEspecifica.setValorDaParcela(rs.getDouble("valor_parcela"));
                    parcelaEspecifica.setEstadoPagamento(rs.getBoolean("estado_pagamento"));
    
                    String dataCriacao = rs.getString("dataCriacao");
                    if (dataCriacao != null) {
                        parcelaEspecifica.setDataCriacaoByString(rs.getString("dataCriacao"));
                    }
    
                    String dataModificacao = rs.getString("dataModificacao");
                    if (dataModificacao != null) {
                        parcelaEspecifica.setDataModificacaoByString(rs.getString("dataModificacao"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    
        return parcelaEspecifica;
    }    

    public Parcela registraParcela(LocalDateTime calendario, String data, Pagamento pagamento, Pessoa pessoa, double valor, int parcelaNmr) {
        Parcela parcela = new Parcela();

        double valorDaParcela = pagamento.getFornecedor().getValorAPagar() / pagamento.getFornecedor().getParcelas();
        parcela.setValorDaParcela(valorDaParcela);

        parcela.setData(data);
        parcela.setPagamento(pagamento);
        parcela.setPagante(pessoa);
        parcela.setValorDaParcela(valorDaParcela);
        parcela.setParcela(parcelaNmr);
        parcela.setEstadoPagamento(verificaParcelaConstrutor(calendario, data));
        parcela.setDataCriacao(calendario);

        return adicionaParcelaBanco(parcela);
    }

    private double calculaValor(Pagamento pagamento) {
        double valorDaParcela = pagamento.getFornecedor().getValorAPagar() / pagamento.getFornecedor().getParcelas();
        return valorDaParcela;
    }

    public void atualizaParcela(int id, int parcelaNmr, String data, Pessoa pessoa, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao, LocalDateTime calendario) {
        Long idLongPagamento = Long.valueOf(id);
        Long idLongParcela = Long.valueOf(parcelaNmr);
        Parcela parcela = buscarParcelaByIdBanco(idLongParcela, pagamentodao, fornecedordao, pessoadao);

        parcela.setValorDaParcela(calculaValor(pagamentodao.buscarPagamentoByIdBanco(idLongPagamento, fornecedordao)));
        parcela.setData(data);
        parcela.setPagante(pessoa);
        parcela.setDataModificacao(calendario);

        atualizaParcelaBanco(parcela);
    }

    public boolean verificaEstadoFornecedor(PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {
        List<Parcela> todasAsParcelas = buscarTodasAsParcelas(pagamentodao, fornecedordao, pessoadao);
        List<Pagamento> todosOsPagamentos = pagamentodao.buscarTodosOsPagamentos(fornecedordao);

        boolean fornecedorAtualizado = false;

        for (Pagamento pagamento : todosOsPagamentos) {

            if (pagamento.getFornecedor() != null) {
                List<Parcela> parcelasDoPagamento = todasAsParcelas.stream()
                        .filter(parcela -> parcela.getPagamento().getId() == pagamento.getId()).toList();

                boolean todasParcelasPagas = parcelasDoPagamento.stream()
                        .allMatch(Parcela::getEstadoPagamento/*parcela -> parcela.getEstadoPagamento()*/);

                if (todasParcelasPagas) {
                    pagamento.getFornecedor().setEstado(true);
                    fornecedordao.atualizaFornecedorBanco(pagamento.getFornecedor());
                    fornecedorAtualizado = true;
                }
            }
        }

        return fornecedorAtualizado;
    }

    public double calculaTotalPagamentos(PagamentoDAO pagamentoDAO, FornecedorDAO fornecedorDAO, PessoaDAO pessoaDAO) {
        double totalPagamentos = 0;

        List<Parcela> todasAsParcelas = buscarTodasAsParcelas(pagamentoDAO, fornecedorDAO, pessoaDAO);

        for (Parcela parcela : todasAsParcelas) {
            if (parcela.getEstadoPagamento()) {
                totalPagamentos += parcela.getValorDaParcela();
            }
        }

        return totalPagamentos;
    }

    public boolean verificaParcelaConstrutor(LocalDateTime calendario, String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataParcela = LocalDate.parse(data, formatter);
        return calendario.toLocalDate().isAfter(dataParcela) || calendario.toLocalDate() == dataParcela;
    }

    public void verificaTodasAsParcelas(LocalDateTime calendario, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {
        List<Parcela> listaParcelas = buscarTodasAsParcelas(pagamentodao, fornecedordao, pessoadao);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //yyyy-MM-dd

        for (Parcela parcela : listaParcelas) {
            LocalDateTime dataParcela = LocalDateTime.parse(parcela.getData(), formatter);

            if (!calendario.isAfter(dataParcela)) {
                parcela.setEstadoPagamento(true);
            } else {
                parcela.setEstadoPagamento(false);
            }
        }
    }

    public String verPagamentosComParcelas(PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, PessoaDAO pessoadao) {
        StringBuilder resultado = new StringBuilder();

        List<Pagamento> todosOsPagamentos = pagamentodao.buscarTodosOsPagamentos(fornecedordao);
        List<Parcela> todasAsParcelas = buscarTodasAsParcelas(pagamentodao, fornecedordao, pessoadao);

        for (Pagamento pagamento : todosOsPagamentos) {
            resultado.append("Pagamento: ").append(pagamento).append("\n");

            for (Parcela parcela : todasAsParcelas) {
                if (parcela.getPagamento().getId() == pagamento.getId()) {
                    resultado.append("   Parcela: ").append(parcela).append("\n");
                }
            }

            resultado.append("\n");
        }

        return resultado.toString();
    }
}
