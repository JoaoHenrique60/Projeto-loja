package br.com.projetoloja.uicontroller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.conta.Conta;
import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerGerarCarner {

	@FXML
	private TextField dataInput;
	@FXML
	private TextField parcelasInput;
	@FXML
	private Label nomeLabel;
	@FXML
	private Label valorTotalLabel;
	@FXML
	private Label dataLabel;
	@FXML
	private Label numeroParcelasLabel;
	@FXML
	private Label valorParcelaLabel;

	public static Cliente clienteGerarCarner = null;

	@FXML
	private void initialize() throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		Cliente c = Fachada.getInstance().clientePesquisarPorId(clienteGerarCarner.getId());
		clienteGerarCarner = c;
		nomeLabel.setText(clienteGerarCarner.getNome());
		valorTotalLabel.setText(String.valueOf(clienteGerarCarner.getConta().getValorTotal()));
	}
	
	@FXML
	private void cancelar() {
		try {
			StartApp.getInstance().telaCliente(new Stage());
			StartApp.stageGerarCarner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void finalizar() {
		try {
			 if(!dataInput.getText().equals("") && !parcelasInput.getText().equals("")){
			 int teste = JOptionPane.showConfirmDialog(null, "Você tem certeza...","Atenção",JOptionPane.YES_NO_OPTION);
			 if(teste == 0){
			clienteGerarCarner.getConta().setQuantidadeParcelas(Integer.parseInt(parcelasInput.getText()));
			clienteGerarCarner.getConta().setValorParcela
			(clienteGerarCarner.getConta().getValorTotal() / Integer.parseInt(parcelasInput.getText()));
			clienteGerarCarner.getConta().setParcelaAtual(0);
			Fachada.getInstance().gerarCaner(clienteGerarCarner.getConta());
			StartApp.getInstance().telaCliente(new Stage());
			StartApp.stageGerarCarner.close();
			 }
			 }else{
			 JOptionPane.showMessageDialog(null, "Preencha os campos acima...");
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizaData(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			dataLabel.setText(String.valueOf(dataInput.getText()));
			parcelasInput.requestFocus();
		}
	}

	public void atualizaParcela(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			numeroParcelasLabel.setText(parcelasInput.getText());
			valorParcelaLabel.setText(String.valueOf(clienteGerarCarner.getConta().getValorTotal()/Integer.parseInt(parcelasInput.getText())));
		}
	}
}
