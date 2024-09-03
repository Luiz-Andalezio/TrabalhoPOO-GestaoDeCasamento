package model;

public class PessoaDAO {
    Pessoa[] pessoa = new Pessoa[100];

    public PessoaDAO(){
        
        Pessoa p1 = new Pessoa();
        p1.setId(1);
        p1.setNome("Luiz");
        p1.setIdade(20);
        p1.setTelefone("+55 34 99713-6908");
        p1.setNascimento("19/04/2004");
        p1.setDataCriacao(null);
        p1.setDataModificacao(null);
        pessoa[0] = p1;
    }
    public Pessoa retornaPessoa(){
            return pessoa[0];
        }
}
