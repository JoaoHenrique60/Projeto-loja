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

/**
 * Created by João Henrique on 18/01/2016.
 */
public class ControllerFinalizarPagamentoCarner {

	@FXML
	private TextField entradaInput;
	@FXML
	private TextField codInput;
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

	private Cliente cliente = null;

	@FXML
	private void initialize() {
		valorCompraLabel.setText(String.valueOf(ControllerHome.totalPagar));
		valorTotalLabel.setText(String.valueOf(ControllerHome.totalPagar));
	}

	@FXML
	private void cancelar() {
		try {
			StartApp.getInstance().telaFinalizarCompra(new Stage());
			StartApp.stageFinalizarPagamentoCarner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void finalizar() {
		try {
			if(!codInput.getText().equals("") && !entradaInput.getText().equals("")){
			int teste = JOptionPane.showConfirmDialog(null, "Você tem certeza...","Atenção",JOptionPane.YES_NO_OPTION);
			if(teste == 0){
			Conta c = new Conta(Integer.parseInt(codInput.getText()),0,ControllerHome.listaProdutoFinalizar,0,
					((ControllerHome.totalPagar + cliente.getConta().getValorTotal())- Double.parseDouble(entradaInput.getText())),0);
			Fachada.getInstance().compraConta(c);			
			 for (Produto p : ControllerHome.listaProdutoFinalizar) {
                 for (int i = p.getQuantidade(); i >0 ; i--) {
                	 System.out.println(i);
                     Fachada.getInstance().produtoFazerCompra(p);
                 }
             }
			StartApp.getInstance().home(new Stage());
			StartApp.stageFinalizarPagamentoCarner.close();
			}
			}else{
				JOptionPane.showMessageDialog(null, "Preencha os campos acima...");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void atualizaEntrada(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (cliente == null) {
				entradaLabel.setText(String.valueOf(entradaInput.getText()));
				valorTotalLabel.setText(
						String.valueOf(ControllerHome.totalPagar - Double.parseDouble(entradaInput.getText())));
				codInput.requestFocus();
			} else {
				entradaLabel.setText(String.valueOf(entradaInput.getText()));
				valorTotalLabel.setText(String.valueOf((cliente.getConta().getValorTotal() + ControllerHome.totalPagar)
						- Double.parseDouble(entradaInput.getText())));
				codInput.requestFocus();
			}
		}
	}

	public void atualizaCodCliente(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			try {
				cliente = Fachada.getInstance().clientePesquisarPorId(Integer.parseInt(codInput.getText()));
				nomeLabel.setText(cliente.getNome());
				valorContaLabel.setText(String.valueOf(cliente.getConta().getValorTotal()));
				if(!entradaInput.getText().equals("")){
				valorTotalLabel.setText(String.valueOf((ControllerHome.totalPagar + cliente.getConta().getValorTotal())
						- Double.parseDouble(entradaInput.getText())));
				entradaInput.requestFocus();
				}else{
					valorTotalLabel.setText(String.valueOf(ControllerHome.totalPagar + cliente.getConta().getValorTotal()));
					entradaInput.requestFocus();
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
}
