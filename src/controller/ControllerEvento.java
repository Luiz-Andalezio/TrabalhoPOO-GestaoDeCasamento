package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.EventoDAO;
import model.IgrejaDAO;
import model.CartorioDAO;
import model.Usuario;
import view.GUI;

public class ControllerEvento {

    public void controllerCrudEvento(GUI gui, Usuario usuarioLogado, EventoDAO eventodao, IgrejaDAO igrejadao, CartorioDAO cartoriodao, LocalDateTime calendario) {
        StringBuilder m;
        int menuEventoOpc = 0;

        while (menuEventoOpc != -1) {
            menuEventoOpc = gui.crudEvento(usuarioLogado, calendario);
            switch (menuEventoOpc) {
                case 1:
                    //Editar nome dos noivos
                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                    String novoNomeNoivo = JOptionPane.showInputDialog("Informe o novo nome do noivo: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String novoNomeNoiva = JOptionPane.showInputDialog("Informe o novo nome da noiva: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    if (!"".equals(novoNomeNoivo) || !"".equals(novoNomeNoiva)) {
                        if (!"".equals(novoNomeNoivo) && !"".equals(novoNomeNoiva)) {
                            JOptionPane.showMessageDialog(null, "Nomes atualizados com sucesso!\n\nNovo nome do noivo: " + novoNomeNoivo + "\nNovo nome da noiva: " + novoNomeNoiva);
                        } else if (!"".equals(novoNomeNoivo)) {
                            eventodao.atualizaNomeNoivo(novoNomeNoivo, calendario);
                            JOptionPane.showMessageDialog(null, "Nome do noivo atualizado com sucesso!");
                        } else if (!"".equals(novoNomeNoiva)) {
                            eventodao.atualizaNomeNoiva(novoNomeNoiva, calendario);
                            JOptionPane.showMessageDialog(null, "Nome da noiva atualizado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum dado enviado: fornecedor não criado...");
                        }
                    }
                    break;

                case 2:
                    //Editar data e horario do evento
                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");

                    String dataAtt = JOptionPane.showInputDialog("\nInforme a nova data e horario do evento (##/##/#### ##:##): ");
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDate.parse(dataAtt, formatador);

                    if (!"".equals(dataAtt)) {
                        eventodao.atualizaDataEvento(dataAtt, calendario);
                        JOptionPane.showMessageDialog(null, "Data atualizada com sucesso!");
                    } else if ("".equals(dataAtt)) {
                        JOptionPane.showMessageDialog(null,
                                "Nenhum dado enviado: atualizações não foram realizadas...");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Formato inválido! Use o formato (##/##/#### ##:##).");
                    }
                    break;

                case 3:
                    //Editar Igreja
                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                    String nomeAtt = JOptionPane.showInputDialog("Informe o novo nome da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String enderecoAtt = JOptionPane.showInputDialog("Informe o novo endereço da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String cepAtt = JOptionPane.showInputDialog("Informe o novo cep da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    if (!"".equals(nomeAtt) || !"".equals(enderecoAtt) || !"".equals(cepAtt)) {
                        igrejadao.atualizaIgreja(nomeAtt, enderecoAtt, cepAtt);
                        JOptionPane.showMessageDialog(null, "Igreja atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: fornecedor não criado...");
                    }
                    break;

                case 4:
                    //Editar Cartório
                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                    nomeAtt = JOptionPane.showInputDialog("Informe o novo nome do cartório: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    enderecoAtt = JOptionPane.showInputDialog("Informe o novo endereço do cartório: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String telefoneAtt = JOptionPane.showInputDialog("Informe o novo telefone do cartório: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    cepAtt = JOptionPane.showInputDialog("Informe o novo cep da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    if (!"".equals(nomeAtt) || !"".equals(enderecoAtt) || !"".equals(cepAtt) || !"".equals(telefoneAtt)) {
                        cartoriodao.atualizaCartorio(nomeAtt, enderecoAtt, telefoneAtt, cepAtt);
                        JOptionPane.showMessageDialog(null, "Cartório atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: fornecedor não criado...");
                    }
                    break;

                case 0:
                    //Voltar
                    menuEventoOpc = -1;
                    break;

                default:
                    menuEventoOpc = -1;
                    break;
            }
        }
    }

    public void controllerInfoEvento(EventoDAO eventodao) {
        JOptionPane.showMessageDialog(null, eventodao.retornaEvento().eventoInfos());
    }
}
