package br.com.projetoloja.uicontroller;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.conta.Conta;
import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by João Henrique on 18/01/2016.
 */
public class ControllerPagamentoParcela {

    @FXML
    private Label codBanco;
    @FXML
    private Label nomeBanco;
    @FXML
    private Label parcelaBanco;
    @FXML
    private Label valorBanco;
    public static Cliente clienteParcela = null;
    @FXML
    private void initialize(){

            try {
                if(clienteParcela != null) {
                    Conta conta = Fachada.getInstance().recuperarConta(clienteParcela.getConta().getId());
                    codBanco.setText(String.valueOf(clienteParcela.getId()));
                    nomeBanco.setText(clienteParcela.getNome());
                    parcelaBanco.setText(String.valueOf(conta.getParcelaAtual()));
                    valorBanco.setText("R$: " + clienteParcela.getConta().getValorParcela());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

    @FXML
    private void cancelar() throws Exception {
        clienteParcela = null;
        StartApp.getInstance().telaCliente(new Stage());
        StartApp.stagePagamentoParcela.close();
    }
    @FXML
    private void finalizar(){

        try {
            Fachada.getInstance().pagamento(clienteParcela.getConta().getId());
            if(clienteParcela.getConta().getQuantidadeParcelas() > clienteParcela.getConta().getParcelaAtual()){
                JOptionPane.showMessageDialog(null,"Parcela Paga com sucesso...");
                initialize();
            }else{
                JOptionPane.showMessageDialog(null,"Fim de conta...");
                clienteParcela = null;
                StartApp.getInstance().telaCliente(new Stage());
                StartApp.stagePagamentoParcela.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
