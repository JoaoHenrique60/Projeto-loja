package br.com.projetoloja.uicontroller;

import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by João Henrique on 29/12/2015.
 */
public class ControllerNovoProduto {

    @FXML
    private TextField nomeInput;
    @FXML
    private TextField quantidadeIntup;
    @FXML
    private TextField quantidadeMinimaInput;
    @FXML
    private TextField valorCompraInput;
    @FXML
    private TextField valorVendaInput;


    @FXML
    private void novo(){
        try {
            Fachada.getInstance().produtoCadastrar(new Produto(0,nomeInput.getText(),Integer.parseInt(quantidadeIntup.getText()),
            Integer.parseInt(quantidadeMinimaInput.getText()),Double.parseDouble(valorCompraInput.getText()),
                    Double.parseDouble(valorVendaInput.getText()),'A'));
            nomeInput.clear();
            quantidadeIntup.clear();
            quantidadeMinimaInput.clear();
            valorCompraInput.clear();
            valorVendaInput.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void finalizar(){
        try {
            if(!nomeInput.getText().equals("") && !quantidadeIntup.getText().equals("") &&
             !quantidadeMinimaInput.getText().equals("") && !valorCompraInput.getText().equals("")&&
             !valorVendaInput.getText().equals("")){
             Fachada.getInstance().produtoCadastrar(new Produto(0, nomeInput.getText(), Integer.parseInt(quantidadeIntup.getText()),
             Integer.parseInt(quantidadeMinimaInput.getText()), Double.parseDouble(valorCompraInput.getText()),
             Double.parseDouble(valorVendaInput.getText()), 'A'));
             StartApp.getInstance().telaProduto(new Stage());
             StartApp.stageNovoProduto.close();
            }else{
                StartApp.getInstance().telaProduto(new Stage());
                StartApp.stageNovoProduto.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cancelar() throws Exception {
        StartApp.getInstance().telaProduto(new Stage());
        StartApp.stageNovoProduto.close();
    }
}
