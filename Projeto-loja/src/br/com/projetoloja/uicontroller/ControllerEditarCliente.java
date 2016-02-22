package br.com.projetoloja.uicontroller;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by João Henrique on 15/01/2016.
 */
public class ControllerEditarCliente {


    @FXML
    private TextField nomeBanco;
    @FXML
    private TextField telefoneBanco;
    @FXML
    private TextField celularBanco;
    @FXML
    private TextField cepBanco;
    @FXML
    private TextField cidadeBanco;
    @FXML
    private TextField bairroBanco;
    @FXML
    private TextField ruaBanco;
    @FXML
    private TextField numeroBanco;
    @FXML
    private TextField complementoBanco;

    public static Cliente clienteEditar = null;

    @FXML
    private void initialize(){
        if(clienteEditar != null){
            nomeBanco.setText(clienteEditar.getNome());
            telefoneBanco.setText(String.valueOf(clienteEditar.getTelefone()));
            celularBanco.setText(String.valueOf(clienteEditar.getCelular()));
            cepBanco.setText(String.valueOf(clienteEditar.getEndereco().getCep()));
            cidadeBanco.setText(String.valueOf(clienteEditar.getEndereco().getCidade()));
            bairroBanco.setText(String.valueOf(clienteEditar.getEndereco().getBairro()));
            ruaBanco.setText(String.valueOf(clienteEditar.getEndereco().getRua()));
            numeroBanco.setText(String.valueOf(clienteEditar.getEndereco().getNumero()));
            complementoBanco.setText(String.valueOf(clienteEditar.getEndereco().getComplemento()));
        }
    }

    @FXML
    public void concluir(){
        try {
            clienteEditar.setNome(String.valueOf(nomeBanco.getText()));
            clienteEditar.setTelefone(telefoneBanco.getText());
            clienteEditar.setCelular(celularBanco.getText());
            clienteEditar.getEndereco().setCep(Integer.parseInt(cepBanco.getText()));
            clienteEditar.getEndereco().setCidade(cidadeBanco.getText());
            clienteEditar.getEndereco().setBairro((bairroBanco.getText()));
            clienteEditar.getEndereco().setRua((ruaBanco.getText()));
            clienteEditar.getEndereco().setNumero((Integer.parseInt(numeroBanco.getText())));
            clienteEditar.getEndereco().setComplemento((complementoBanco.getText()));

            Fachada.getInstance().clienteAlterar(clienteEditar);
            StartApp.getInstance().telaCliente(new Stage());
            StartApp.stageEditarCliente.close();
            clienteEditar = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}