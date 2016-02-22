package br.com.projetoloja.uicontroller;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by João Henrique on 18/01/2016.
 */
public class ControllerTrocarPecaCliente {

    @FXML
    private Label nomeBanco;
    @FXML
    private Label novoProduto;
    @FXML
    private Label produtoAntigo;
    @FXML
    private TextField codProdutoNovo;
    @FXML
    private TextField codProdutoAntigo;

    public static Cliente clienteTrocar = null;

    @FXML
    private void initialize(){
        if(clienteTrocar != null){
            nomeBanco.setText(clienteTrocar.getNome());
        }
    }

    @FXML
    private void finalizar(){

    }
    @FXML
    private void voltar() throws Exception {
        StartApp.getInstance().telaProduto(new Stage());
        StartApp.stageTrocarPecaCliente.close();
    }
    
    @FXML
    private void inputNovoProduto(KeyEvent event){
    	if(event.getCode() == KeyCode.ENTER){
    		
    	}
    }
    
    @FXML
    private void inputProdutoAmtigo(KeyEvent event){
    	if(event.getCode() == KeyCode.ENTER){
    		
    		codProdutoNovo.requestFocus();	
    	}
    }
    
}
