package controller;

import model.Pessoa; import model.PessoaDAO;
import model.Usuario; import model.UsuarioDAO;

public class Main {
    public static void main(String[] args) {       
        
        //Testes
        PessoaDAO pessoadao = new PessoaDAO();
        Pessoa p1 = pessoadao.retornaPessoa();
                
        UsuarioDAO usuariodao = new UsuarioDAO(p1);
        Usuario u1 = usuariodao.retornaUsuario();
        
        System.out.println(p1);
        System.out.println(u1);
    }    
}
