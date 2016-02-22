package br.com.projetoloja.uicontroller;

import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;

public class ControllerLogin {

	@FXML
	private TextField usuario;
	@FXML
	private PasswordField senha;

	@FXML
	private void fazerLogin(){
		try{
			if (Fachada.getInstance().administradorLogin(usuario.getText(), senha.getText()) != null) {
				StartApp.stageLogin.close();
				StartApp.stageLogin = null;
				StartApp.getInstance().home(new Stage());
			} else {
				JOptionPane.showMessageDialog(null, "Login e/ou senha invílidos", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e){
			
		}
	}

	@FXML
	private void fazerLoginEnter(KeyEvent event) throws Exception {
		if(event.getCode() == KeyCode.ENTER) {
			if (Fachada.getInstance().administradorLogin(usuario.getText(), senha.getText()) != null) {
				StartApp.stageLogin.close();
				StartApp.stageLogin = null;
				StartApp.getInstance().home(new Stage());
			} else {
				JOptionPane.showMessageDialog(null, "Login e/ou senha invílidos", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
