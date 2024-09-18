package controller;

import javax.swing.JOptionPane;
import model.PessoaDAO;
import view.GUI;

public class ControllerConvidadoIndividual {

    GUI gui = new GUI();
    PessoaDAO pessoadao = new PessoaDAO();

    public void controllerCrudConvidado() {
        StringBuilder m = new StringBuilder();
        int menuPessoaOpc = 0;

        while (menuPessoaOpc != -1) {
            menuPessoaOpc = gui.crudConvIndividual();
            switch (menuPessoaOpc) {
                case 1:
                    m.append("\n Informe o nome do(a) convidado(a).");
                    String nome = JOptionPane.showInputDialog(m);

                    //m.append("\nInforme o telefone de " + nome);
                    m.append("\nInforme o telefone de ").append(nome);
                    String telefone = JOptionPane.showInputDialog(m);

                    m.append("\nInforme a data de nascimendo de ").append(nome);
                    String nascimento = JOptionPane.showInputDialog(m);

                    pessoadao.criarPessoa(nome, telefone, nascimento);

                    m.append("Pessoa criada com sucesso!\n").append("Nome: ").append(nome).append("\nTelefone: ").append(telefone).append("Data de nascimento: ").append(nascimento);
                    /*
                    p3.setId(3);
                    p3.setNome("Rodolfo");
                    p3.setTelefone("34996839138");
                    p3.setNascimento("02/12/1993");
                    */
                    break;

                    case 2: 

                        break;
            }
        }
    }
}
