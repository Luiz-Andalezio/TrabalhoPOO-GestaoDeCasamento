package controller;

import javax.swing.JOptionPane;

import model.Pessoa; import model.PessoaDAO;
import model.Usuario; import model.UsuarioDAO;

public class Main {
    public static void main(String[] args) {       
        
        //Testes
        PessoaDAO pessoadao1 = new PessoaDAO(1, "Luiz", 20, "991650733");
        Pessoa p1 = pessoadao1.retornaPessoa();
                
        UsuarioDAO usuariodao = new UsuarioDAO(p1, "noivo", "coxinha", "123");
        Usuario u1 = usuariodao.retornaUsuario();        
        
        System.out.println(p1);
        System.out.println(u1);
        
        //Testes com JOptionPane
        PessoaDAO pessoadao0 = new PessoaDAO(0, null, 0, null);
        Pessoa p0 = pessoadao0.retornaPessoa();
        
        JOptionPane.showConfirmDialog(null, "Preparado para criar uma pessoa com usuario?");
        p0.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira um id: ")));
        p0.setNome(JOptionPane.showInputDialog("Insira um nome: "));
        p0.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Insira uma idade: ")));
        p0.setTelefone(JOptionPane.showInputDialog("Insiga um telefone: "));
        p0.setNascimento(JOptionPane.showInputDialog("Insina um nascimento: "));
        
        JOptionPane.showConfirmDialog(null, p0 + " \nEstes sao os dados desejados?");
        JOptionPane.showMessageDialog(null, "Dados confirmados!\n\nPessoa:\n" + p0);    
                
        JOptionPane.showConfirmDialog(null, "Preparado para criar um usuario para esta pessoa?");
        
        UsuarioDAO usuariodao0 = new UsuarioDAO(p0, null, null, null);
        Usuario u0 = usuariodao0.retornaUsuario();
        
        u0.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira um id: ")));
        u0.setLogin(JOptionPane.showInputDialog("Insira um login: "));
        u0.setSenha(JOptionPane.showInputDialog("Insira uma senha: "));
        
        JOptionPane.showConfirmDialog(null, u0 + "\nEstes são os dados finais do " + p0.getNome() + " e seu usuario de id: " + u0.getId());
        JOptionPane.showMessageDialog(null, "Usuario criado com sucesso!\n\nDados:\n" + u0);
    }    
}
