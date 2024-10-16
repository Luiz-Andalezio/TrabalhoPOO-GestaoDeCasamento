package model;

import java.time.LocalDateTime;

public class EventoDAO {
    Evento[] eventos = new Evento[100];

    public EventoDAO(PessoaDAO pessoadao, LocalDateTime calendario) {
        Evento e = new Evento();
        e.setId(1);
        e.setData("25/12/2024 18:30");
        //e1.setCerimonial();
        //e1.setIgreja();
        //e1.setCartorio();
        e.setPessoaNoivo(pessoadao.retornaPessoa(0));
        e.setPessoaNoiva(pessoadao.retornaPessoa(1));
        e.setDataCriacao(calendario);
        eventos[0] = e;
        }    

    public Evento retornaEvento(){
        return eventos[0];
    }
}
