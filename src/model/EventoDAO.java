package model;

public class EventoDAO {
    Evento[] eventos = new Evento[100];

    public EventoDAO(PessoaDAO pessoadao) {
        Evento e = new Evento();
        e.setId(1);
        e.setData("25/12/2024");
        //e1.setCerimonial();
        //e1.setIgreja();
        //e1.setCartorio();
        e.setPessoaNoivo(pessoadao.retornaPessoa(0));
        e.setPessoaNoiva(pessoadao.retornaPessoa(1));
        eventos[0] = e;
        }    

    public Evento retornaEvento(){
        return eventos[0];
    }
}
