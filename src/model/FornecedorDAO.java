package model;

import java.time.LocalDate;

public class FornecedorDAO {

    private Fornecedor[] fornecedores = new Fornecedor[100];

    public FornecedorDAO() {
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setNome("Buffet Margaux");
        fornecedor1.setCnpj("12.345.678/0001-99");
        fornecedor1.setTelefone("(21) 9876-5432");
        fornecedor1.setValorAPagar(25000.00); 
        fornecedor1.setParcelas(5); 
        fornecedor1.setEstado(false); 
        fornecedor1.setDataCriacao(); 

        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setId(2);
        fornecedor2.setNome("Três Marias: Decoração Elegante");
        fornecedor2.setCnpj("98.765.432/0001-88");
        fornecedor2.setTelefone("(11) 9876-5432");
        fornecedor2.setValorAPagar(8000.00);
        fornecedor2.setParcelas(4);
        fornecedor2.setEstado(false);
        fornecedor2.setDataCriacao();

        Fornecedor fornecedor3 = new Fornecedor();
        fornecedor3.setId(3);
        fornecedor3.setNome("Alok DJ");
        fornecedor3.setCnpj("12.345.678/0001-99");
        fornecedor3.setTelefone("(11) 99735-4261");
        fornecedor3.setValorAPagar(680000.00);
        fornecedor3.setParcelas(34);
        fornecedor3.setEstado(false);
        fornecedor3.setDataCriacao();

        fornecedores[0] = fornecedor1;
        fornecedores[1] = fornecedor2;
        fornecedores[2] = fornecedor3;
    }

    public boolean registraFornecedor(String nome, String cnpj, String telefone, double valorAPagar, int parcelas) {
        for (int i = 0; i < fornecedores.length; i++) {
            if (fornecedores[i] == null) {
                fornecedores[i] = new Fornecedor();
                fornecedores[i].setNome(nome);
                fornecedores[i].setCnpj(cnpj);
                fornecedores[i].setTelefone(telefone);
                fornecedores[i].setValorAPagar(valorAPagar);
                fornecedores[i].setParcelas(parcelas);
                fornecedores[i].setDataCriacao();
                return true;
            }
        }
        return false;
    }

    public boolean atualizaFornecedor(String nome, String cnpj, String telefone, double valorAPagar, int parcelas, int id) {
        int i = 0;
        while (fornecedores[i] != null && fornecedores[i].getId() != id || fornecedores[i] == null) {
            i++;
        }

        if (fornecedores[i] != null && fornecedores[i].getId() == id) {
            if (!nome.equals("")) {
                fornecedores[i].setNome(nome);
            }
            if (!cnpj.equals("")) {
                fornecedores[i].setCnpj(cnpj);
            }
            if (!telefone.equals("")) {
                fornecedores[i].setTelefone(telefone);
            }
            fornecedores[i].setValorAPagar(valorAPagar);
            fornecedores[i].setParcelas(parcelas);
            fornecedores[i].setDataModificacao();
            return true;
        }
        return false;
    }

    public void excluirFornecedor(int id) {
        int i = 0;
        while (fornecedores[i] != null && fornecedores[i].getId() != id || fornecedores[i] == null) {
            i++;
        }

        if (fornecedores[i] != null && fornecedores[i].getId() == id) {
            fornecedores[i] = null;
        }
    }

    public boolean pagarFornecedor(int id, boolean registro, LocalDate calendario) {
        int i = 0;
        while (fornecedores[i] != null && fornecedores[i].getId() != id || fornecedores[i] == null) {
            i++;
        }

        if (fornecedores[i] != null && fornecedores[i].getId() == id) {
            if (registro != false) {
                fornecedores[i].setEstado(registro);
                fornecedores[i].setDataModificacao();
            }
            return true;
        }
        return false;
    }

    public String verFornecedores() {
        String m = "";
        for (int i = 0; i < fornecedores.length; i++) {
            if (fornecedores[i] != null) {
                m += fornecedores[i].toString() + "\n";
            }
        }
        return m;
    }

    public Fornecedor verFornecedor(int id) {
        return fornecedores[id - 1];
    }

    public Fornecedor retornaFornecedorVetor(int i) {
        return fornecedores[i];
    }
}
