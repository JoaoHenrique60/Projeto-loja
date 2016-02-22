package br.com.projetoloja.uicontroller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
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
public class ControllerTrocarPeca {

	@FXML
	private Label novoProdutoLabel;
	@FXML
	private Label produtoAntigoLabel;
	@FXML
	private TextField codProdutoNovo;
	@FXML
	private TextField codProdutoAntigo;

	private Produto produtoNovo = null;
	private Produto produtoAntido = null;

	@FXML
	private void finalizar() {
		if(!codProdutoAntigo.getText().equals("") && !codProdutoNovo.getText().equals("")){
			try {
				JOptionPane.showConfirmDialog(null, "Você tem certeza","atenção",JOptionPane.YES_NO_OPTION);

				produtoAntido.setQuantidade(produtoAntido.getQuantidade() +1);
				produtoNovo.setQuantidade(produtoNovo.getQuantidade() -1);
				
				Fachada.getInstance().produtoAlterar(produtoAntido);
				Fachada.getInstance().produtoAlterar(produtoNovo);	
			} catch (NaoFoiPossivelAlterarException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CampoObritarorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NaoEncontradoException e) {
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

	@FXML
	private void voltar() throws Exception {
		StartApp.getInstance().telaProduto(new Stage());
		StartApp.stageTrocarPeca.close();
	}

	public void inputNovoProduto(KeyEvent event) {
		try {
			if (event.getCode() == KeyCode.ENTER) {
				produtoNovo = Fachada.getInstance().produtoPesquisarPorId(Integer.parseInt(codProdutoNovo.getText()));
				if (produtoNovo != null) {
					novoProdutoLabel.setText(produtoNovo.getNome());
				}
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

	public void inputProdutoAntigo(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			try {
				System.out.println("foi");
				produtoAntido = Fachada.getInstance()
						.produtoPesquisarPorId(Integer.parseInt(codProdutoAntigo.getText()));
				produtoAntigoLabel.setText(produtoAntido.getNome());
				codProdutoNovo.requestFocus();
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
