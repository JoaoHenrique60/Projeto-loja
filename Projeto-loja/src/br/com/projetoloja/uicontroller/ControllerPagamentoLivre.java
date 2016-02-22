package br.com.projetoloja.uicontroller;

import br.com.projetoloja.cliente.Cliente;
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
public class ControllerPagamentoLivre {

    @FXML
    private Label codBanco;
    @FXML
    private Label nomeBanco;
    @FXML
    private Label valorBanco;
    @FXML
    private TextField valorInput;

    public static Cliente clientePagamentoLivre = null;

    @FXML
    private void initialize(){
        if(clientePagamentoLivre != null){
        	valorBanco.setText(String.valueOf(clientePagamentoLivre.getConta().getValorTotal()));
            codBanco.setText(String.valueOf(clientePagamentoLivre.getId()));
            nomeBanco.setText(clientePagamentoLivre.getNome());
        }
    }

    @FXML
    private void pagar() throws Exception {
        if(!valorInput.getText().equals("")){
            clientePagamentoLivre.getConta().setValorParcela(Double.parseDouble(valorInput.getText()));
            Fachada.getInstance().pagamentoLivre(clientePagamentoLivre.getConta());
            JOptionPane.showMessageDialog(null,"Pagamento concluido com sucesso...");
            clientePagamentoLivre = null;
            StartApp.getInstance().telaCliente(new Stage());
            StartApp.stagePagamentoLivre.close();
        }
    }

    @FXML
    private void voltar(){
        try {
            clientePagamentoLivre = null;
            StartApp.getInstance().telaCliente(new Stage());
            StartApp.stagePagamentoLivre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
