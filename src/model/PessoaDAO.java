package model;

public class PessoaDAO {

    Pessoa[] pessoas = new Pessoa[100];

    public PessoaDAO() {
        Pessoa p1 = new Pessoa();
        p1.setNome("João");
        p1.setTelefone("34997212965");
        p1.setNascimento("23/02/2000");
        p1.setDataCriacao();
        pessoas[0] = p1;

        Pessoa p2 = new Pessoa();
        p2.setNome("Maria");
        p2.setTelefone("34993126786");
        p2.setNascimento("01/12/2003");
        p2.setDataCriacao();
        pessoas[1] = p2;

        Pessoa p3 = new Pessoa();
        p3.setNome("Rodolfo");
        p3.setTelefone("34996839138");
        p3.setNascimento("02/12/1993");
        p3.setDataCriacao();
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

    public PessoaDAO(String n, String m, String t) { //, String t){
        //Construtor automatizado
        Pessoa p = new Pessoa();
        p.setNome(n);
        p.setTelefone(m);
        p.setNascimento(t);
        p.setDataCriacao();
        p.setDataModificacao();
        for (int v = 0; v < pessoas.length; v++) {
            if (pessoas[v] == null) {
                pessoas[v] = p;
            }
        }
    }

    public boolean criarPessoa(String nome, String telefone, String nascimento, PessoaDAO pessoadao) {
        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setTelefone(telefone);
        p.setNascimento(nascimento);
        p.setDataCriacao();

        for (int v = 0; v < pessoas.length; v++) {
            if (pessoas[v] == null) {
                pessoas[v] = p;
            return true;//StringBuilder m = new StringBuilder("Convidado criado com sucesso!\n").append(pessoadao.verPessoa());
            }
        } return false;//StringBuilder m = new StringBuilder("Pessoa não criada.");
    }

    public boolean atualizaPessoa(String nome, String telefone, String nascimento, int id){
        int i = 0;
        while(pessoas[i] != null && pessoas[i].getId() != id)
        {
            i++;
        }

        if(pessoas[i] != null && pessoas[i].getId() == id) {
            if(!nome.equals("")) {
                pessoas[i].setNome(nome);
            } 
            if(!telefone.equals("")){
                pessoas[i].setTelefone(telefone);
            }
            if(!nascimento.equals("")){
                pessoas[i].setNascimento(nascimento);
            }
            pessoas[i].setDataModificacao();
            return true;
        } 
        return false;
    }

    public void desconvidarPessoa(int id){
        int i = 0;
        while(pessoas[i] != null && pessoas[i].getId() != id)
        {
            i++;
        }

        if(pessoas[i] != null && pessoas[i].getId() == id) {
            pessoas[i] = null;
        } 
    }

    public String verConvidados() {
        String m = "";
        /*
        for (int i = 0; i < pessoas.length; i++) {
        if (pessoas[i] != null) {
        m += pessoas[i].toString() + "\n";
        }
        }
         */
        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                m += pessoa.toString() + "\n";
            }
        }
        return m;
    }

    public String verPessoa() {
        String m = "";
        /*
        for (int i = 0; i < pessoas.length; i++) {
        if (pessoas[i] != null) {
        m = pessoas[i].toString() + "\n";
        }
        }
        */
        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                m = pessoa.toString() + "\n";
            }
        }
        return m;
    }

    public Pessoa verPessoa(int id) {
        return pessoas[id-1];
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
