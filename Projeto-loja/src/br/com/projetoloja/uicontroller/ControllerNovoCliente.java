package br.com.projetoloja.uicontroller;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.endereco.Endereco;
import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by João Henrique on 15/01/2016.
 */
public class ControllerNovoCliente {

    @FXML
    private TextField nomeInput;
    @FXML
    private TextField cpfIntup;
    @FXML
    private TextField telefoneInput;
    @FXML
    private TextField celularInput;
    @FXML
    private TextField cepInput;
    @FXML
    private TextField cidadeInput;
    @FXML
    private TextField bairroIntup;
    @FXML
    private TextField ruaInput;
    @FXML
    private TextField numeroInput;
    @FXML
    private TextField complementoInput;


    @FXML
    private void novo(){
        try {
            Fachada.getInstance().clienteCadastrar(new Cliente(0, nomeInput.getText(), cpfIntup.getText(),
            telefoneInput.getText(), celularInput.getText(),new Endereco(Integer.parseInt(cepInput.getText()),
            cidadeInput.getText(), bairroIntup.getText(),ruaInput.getText(),Integer.parseInt(numeroInput.getText()),
            complementoInput.getText()), null,'A'));
            nomeInput.clear();
            cpfIntup.clear();
            telefoneInput.clear();
            celularInput.clear();
            cepInput.clear();
            cidadeInput.clear();
            bairroIntup.clear();
            ruaInput.clear();
            numeroInput.clear();
            complementoInput.clear();
            nomeInput.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void finalizar(){
        try {
            if(!nomeInput.getText().equals("") && !cpfIntup.getText().equals("") &&
                    !telefoneInput.getText().equals("") && !celularInput.getText().equals("")&&
                    !cepInput.getText().equals("") && !cidadeInput.getText().equals("") &&
                    !bairroIntup.getText().equals("") && !ruaInput.getText().equals("") &&
                    !numeroInput.getText().equals("") && !complementoInput.getText().equals("")){
                Fachada.getInstance().clienteCadastrar(new Cliente(0, nomeInput.getText(), cpfIntup.getText(), telefoneInput.getText(), celularInput.getText(),
                new Endereco(Integer.parseInt(cepInput.getText()), cidadeInput.getText(), bairroIntup.getText(),
                ruaInput.getText(),Integer.parseInt(numeroInput.getText()), complementoInput.getText()), null,'A'));
                StartApp.getInstance().telaCliente(new Stage());
                StartApp.stageNovoCliente.close();
            }else{
                StartApp.getInstance().telaCliente(new Stage());
                StartApp.stageNovoCliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cancelar() throws Exception {
        StartApp.getInstance().telaCliente(new Stage());
        StartApp.stageNovoCliente.close();
    }
}
