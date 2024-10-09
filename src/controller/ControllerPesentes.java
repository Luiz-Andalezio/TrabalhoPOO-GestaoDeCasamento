package controller;

import javax.swing.JOptionPane;
import model.PresentesDAO;
import model.Usuario;
import view.GUI;

public class ControllerPesentes {

    public void controllerCrudPresentes(GUI gui, Usuario usuarioLogado, PresentesDAO presentesdao) {
        String s = presentesdao.verPresentesAdmin(usuarioLogado);
        if ("".equals(s)) {
            JOptionPane.showMessageDialog(null, "Ainda não há presentes comprados.");
        } else {
        }
    }
    
    public void controllerVerPresentes(GUI gui, Usuario usuarioLogado, PresentesDAO presentesdao) {
        String s = presentesdao.verPresentesAdmin(usuarioLogado);
        if ("".equals(s)) {
            JOptionPane.showMessageDialog(null, "Ainda não há presentes comprados.");
        } else {
        }
    }

    public void controllerDarPresentes(GUI gui, PresentesDAO presentesdao) {
        StringBuilder m;
        int menuDarPresentes = 0;

        while (menuDarPresentes != -1) {
            String s = presentesdao.verPresentesConvidado();
            if ("".equals(s)) {
                JOptionPane.showMessageDialog(null, "Ainda não há presentes disponíveis.");
            }
            int id = gui.crudPresentesConvidado(presentesdao);
            if (id != 0) {
                boolean verifica = presentesdao.verificaPresente(id);
                if (verifica == true) {
                    JOptionPane.showMessageDialog(null, "Presente já possui! Escolha outro.");
                } else {
                    int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo comprar o presente abaixo?\n\n" + presentesdao.verPresentesConvidado(id), "Confirmar Atualização", JOptionPane.YES_NO_OPTION);
                    if (veredito == JOptionPane.YES_OPTION) {
                        String nomeComprador = JOptionPane.showInputDialog("Insira o seu nome: ");
                        String nomeCartao = JOptionPane.showInputDialog("Insira o nome informado no seu cartão de crédito: ");
                        String numeroCartao = JOptionPane.showInputDialog("Insira o número informado no seu cartão de crédito: ");
                        String dataVencimento = JOptionPane.showInputDialog("Insira a data de vencimento informada no seu cartão de crédito: ");
                        String cvv = JOptionPane.showInputDialog("Insira o cvv informado no seu cartão de crédito: ");
                        JOptionPane.showMessageDialog(null, "Presente abaixo comprado com sucesso! " + presentesdao.verPresentesConvidado(id));
                        presentesdao.compraPresente(nomeComprador, id);
                    } else {
                        JOptionPane.showMessageDialog(null, "Compra não sucedida");
                    }
                }
            } else {
                menuDarPresentes = -1;
            }
        }
    }
}