package model;

import java.time.LocalDate;

public class ConvidadoFamilia {
    private int id;
    private String nomeDaFamilia;
    private ConvidadoIndividual conviteFamilia;
    private boolean acesso;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDaFamilia() {
        return nomeDaFamilia;
    }

    public void setNomeDaFamilia(String nomeDaFamilia) {
        this.nomeDaFamilia = nomeDaFamilia;
    }
    
    public ConvidadoIndividual conviteFamilia(){
        return conviteFamilia;
    }   
    
    public void setConvidadoIndividualDAO(ConvidadoIndividual conviteFamilia){
        this.conviteFamilia = conviteFamilia;
    }

    public boolean isAcesso() {
        return acesso;
    }

    public void setAcesso(boolean acesso) {
        this.acesso = acesso;
    }
    
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = LocalDate.now();
    }   
}
