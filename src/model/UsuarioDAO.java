package model;

public class UsuarioDAO {
    Usuario[] usuario = new Usuario[100];

    public UsuarioDAO(Pessoa pessoa, String t, String l, String s) {  
        //Construtor automatizado
        Usuario u = new Usuario();
        u.setPessoa(pessoa);
        u.setTipo(t);
        u.setLogin(l);
        u.setSenha(s);  
        usuario[1] = u;        
        
        /*Usuario u = new Usuario();
        u.setPessoa(pessoa);
        u.setTipo("noivo");
        u.setLogin("luiz");
        u.setSenha("123");  
        usuario[1] = u;*/
    }   
    
    public void criarUsuario(Pessoa pessoa, String tipo, String login, String senha){
        Usuario u = new Usuario();
        u.setPessoa(pessoa);
        u.setTipo("noivo");
        u.setLogin("luiz");
        u.setSenha("123");  
        usuario[0] = u;
    }
    
    public Usuario retornaUsuario(){
        return usuario[1];        
    }
    
}
