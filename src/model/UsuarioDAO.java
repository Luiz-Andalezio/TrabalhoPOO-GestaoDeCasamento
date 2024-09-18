package model;

public class UsuarioDAO {

    Usuario[] usuarios = new Usuario[100];

    public UsuarioDAO(PessoaDAO pessoadao) {
        Usuario u1 = new Usuario();
        u1.setPessoa(pessoadao.retornaPessoa(0));
        u1.setLogin("coxinho");
        u1.setSenha("123");
        u1.setTipo("Noivo");
        usuarios[0] = u1;

        Usuario u2 = new Usuario();
        u2.setPessoa(pessoadao.retornaPessoa(1));
        u2.setLogin("coxinha");
        u2.setSenha("123");
        u2.setTipo("Noiva");
        usuarios[1] = u2;

        Usuario u3 = new Usuario();
        u3.setPessoa(pessoadao.retornaPessoa(2));
        u3.setLogin("cerimonio");
        u3.setSenha("123");
        u3.setTipo("Cerimonial");
        usuarios[2] = u3;
    }

    public UsuarioDAO(Pessoa pessoa, String t, String l, String s) {
        //Construtor automatizado
        Usuario u = new Usuario();
        u.setPessoa(pessoa);
        u.setTipo(t);
        u.setLogin(l);
        u.setSenha(s);

        for (int v = 0; v < usuarios.length; v++) {
            if (usuarios[v] == null) {
                usuarios[v] = u;
            }
        }
    }

    public Usuario retornaUsuario(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u != null && login.equals(u.getLogin()) && senha.equals(u.getSenha())) {
                return u;
            }
        }
        return null;
    }

    public Usuario retornaUsuario(int i, String login, String senha) {
        return usuarios[i];
    }
}
