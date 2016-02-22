package br.com.projetoloja.uicontroller;

import java.sql.SQLException;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.fachada.Fachada;
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
public class GerarCarner {

	@FXML
	private TextField entradaInput;
	@FXML
	private TextField codInput;
	@FXML
	private TextField parcelasInput;
	@FXML
	private TextField dataInput;
	@FXML
	private Label nomeLabel;
	@FXML
	private Label valorCompraLabel;
	@FXML
	private Label valorContaLabel;
	@FXML
	private Label entradaLabel;
	@FXML
	private Label valorTotalLabel;
	@FXML
	private Label valorParcelaLabel;
	@FXML
	private Label dataLabel;
	@FXML
	private Label numeroParcelasLabel;

	private Cliente c = null;
	
	
	@FXML
	private void initialize(){
		valorCompraLabel.setText(String.valueOf(ControllerHome.totalPagar));
		valorTotalLabel.setText(String.valueOf(ControllerHome.totalPagar));
	}
	
	@FXML
	private void cancelar(){
		try {
			StartApp.getInstance().telaFinalizarCompra(new Stage());
			StartApp.stageFinalizarPagamentoCarner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void finalizar(){
		
	}
		
	public void atualizaEntrada(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER){
			if(c == null){
			entradaLabel.setText(String.valueOf(entradaInput.getText()));
			valorTotalLabel.setText(String.valueOf
			(ControllerHome.totalPagar - Double.parseDouble(entradaInput.getText())));
			codInput.requestFocus();
			}else{
				entradaLabel.setText(String.valueOf(entradaInput.getText()));
				valorTotalLabel.setText(String.valueOf
				((c.getConta().getValorTotal() + ControllerHome.totalPagar) - Double.parseDouble(entradaInput.getText())));
				codInput.requestFocus();
				if(!parcelasInput.getText().equals("")){
					valorParcelaLabel.setText(String.valueOf(
							Double.parseDouble(valorTotalLabel.getText())/ Integer.parseInt(parcelasInput.getText())));
				}		
			}				
		}
	}
	
	public void atualizaCodCliente(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER){
			try {
				c = Fachada.getInstance().clientePesquisarPorId(Integer.parseInt(codInput.getText()));
				nomeLabel.setText(c.getNome());
				valorContaLabel.setText(String.valueOf(c.getConta().getValorTotal()));
				valorTotalLabel.setText(
				String.valueOf((ControllerHome.totalPagar + c.getConta().getValorTotal()) - Double.parseDouble(entradaInput.getText())));
				parcelasInput.requestFocus();
				if(!parcelasInput.getText().equals("")){
					valorParcelaLabel.setText(String.valueOf(
							Double.parseDouble(valorTotalLabel.getText())/ Integer.parseInt(parcelasInput.getText())));
				}	
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NaoEncontradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CampoObritarorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void atualizaParcela(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER){
			numeroParcelasLabel.setText(String.valueOf(parcelasInput.getText()));
			valorParcelaLabel.setText(String.valueOf(
					Double.parseDouble(valorTotalLabel.getText())/ Integer.parseInt(parcelasInput.getText())));
			dataInput.requestFocus();
		}
	}
	
	public void atualizaData(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER){
			dataLabel.setText(String.valueOf(dataInput.getText()));
		}
	}
}
