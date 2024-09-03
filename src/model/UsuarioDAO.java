package model;

public class UsuarioDAO {
    Usuario[] usuario = new Usuario[100];

    public UsuarioDAO(Pessoa pessoa) {  
        Usuario u = new Usuario();
        u.setPessoa(pessoa);
        u.setTipo("noivo");
        u.setLogin("luiz");
        u.setSenha("123");  
        usuario[1] = u;
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
