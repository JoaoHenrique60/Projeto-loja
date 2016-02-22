package br.com.projetoloja.uicontroller;

import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by João Henrique on 16/01/2016.
 */
public class ControllerFinalizarCompra {

    @FXML
    private Label totalLabel;
    @FXML
    private Label avistaLabel;

    @FXML
    private void initialize(){
        totalLabel.setText(String.valueOf(ControllerHome.totalPagar));
        avistaLabel.setText(String.valueOf(ControllerHome.totalPagar - (ControllerHome.totalPagar * 0.1)));
    }

    @FXML
    private void cancelarCompra() throws Exception {
        ControllerHome.totalPagar = 00;
        ControllerHome.listaProdutoFinalizar = new ArrayList<>();
        StartApp.getInstance().home(new Stage());
        StartApp.stagefinalizarCompra.close();

    }

    @FXML
    private void avista(){
         try {
       int teste = JOptionPane.showConfirmDialog(null, "Você tem certeza...","Atenção",JOptionPane.YES_NO_OPTION);
         if(teste == 0) {
             for (Produto p : ControllerHome.listaProdutoFinalizar) {
                 for (int i = 0; i < p.getQuantidade(); i++) {
                     Fachada.getInstance().produtoFazerCompra(p);
                 }
             }
             ControllerHome.totalPagar = 00;
             ControllerHome.listaProdutoFinalizar = new ArrayList<>();
             StartApp.getInstance().home(new Stage());
             StartApp.stagefinalizarCompra.close();
         }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void carner() throws Exception {
        StartApp.getInstance().finalizarPagamentoCarner(new Stage());
        StartApp.stagefinalizarCompra.close();
    }
}
