package model;

public class PessoaDAO {

    Pessoa[] pessoas = new Pessoa[100];

    public PessoaDAO() {
        Pessoa p1 = new Pessoa();
        p1.setId(1);
        p1.setNome("João");
        p1.setTelefone("34997212965");
        p1.setNascimento("23/02/2000");
        pessoas[0] = p1;

        Pessoa p2 = new Pessoa();
        p2.setId(2);
        p2.setNome("Maria");
        p2.setTelefone("34993126786");
        p2.setNascimento("01/12/2003");
        pessoas[1] = p2;

        Pessoa p3 = new Pessoa();
        p3.setId(3);
        p3.setNome("Rodolfo");
        p3.setTelefone("34996839138");
        p3.setNascimento("02/12/1993");
        pessoas[2] = p3;
        /*
        p1.setId(1);
        p1.setNome("Luiz");
        p1.setIdade(20);
        p1.setTelefone("+55 34 99713-6908");
        p1.setNascimento("19/04/2004");
        p1.setDataCriacao(null);
        p1.setDataModificacao(null);
        pessoa[0] = p1;*/
    }

    public PessoaDAO(int i, String n, String t, String m) { //, String t){
        //Construtor automatizado
        /*Pessoa p = new Pessoa();
        p.setId(i);
        p.setNome(n);
        p.setTelefone(m);
        p.setNascimento(t);
        p.setDataCriacao(null);
        p.setDataModificacao(null);
        pessoas[0] = p;*/
    }

    public void criarPessoa(String nome, String telefone, String nascimento)
    {
        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setTelefone(telefone);
        p.setNascimento(nascimento);

        for (int v = 0; v < pessoas.length; v++) {
            if (pessoas[v] == null) {
                pessoas[v] = p;
            }
        }
    }

        

    public Pessoa retornaPessoa(int i) {
        return pessoas[i];
    }

    /*
    public Pessoa retornaPessoa1() {
        return pessoas[0];
    }

    public Pessoa retornaPessoa2() {
        return pessoas[1];
    }*/
}
