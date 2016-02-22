package br.com.projetoloja.uicontroller;

import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import com.itextpdf.text.log.SysoCounter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by João Henrique on 14/01/2016.
 */
public class ControllerEditarProduto {

    @FXML
    private Label idBanco;
    @FXML
    private TextField nomeBanco;
    @FXML
    private TextField quantidadeBanco;
    @FXML
    private TextField quantidadeMinimaBanco;
    @FXML
    private TextField valorCompraBanco;
    @FXML
    private TextField valorVendaBanco;
    @FXML
    private Label ativoBanco;

    public static Produto produtoEditar = null;

    @FXML
    private void initialize(){
        if(produtoEditar != null){
            idBanco.setText(String.valueOf(produtoEditar.getId()));
            nomeBanco.setText(produtoEditar.getNome());
            quantidadeBanco.setText(String.valueOf(produtoEditar.getQuantidade()));
            quantidadeMinimaBanco.setText(String.valueOf(produtoEditar.getQuantidadeMinima()));
            valorCompraBanco.setText(String.valueOf(produtoEditar.getValorCompra()));
            valorVendaBanco.setText(String.valueOf(produtoEditar.getValorVenda()));
            ativoBanco.setText(String.valueOf(produtoEditar.getAtivo()));
        }
    }

    @FXML
    public void concluir(){
        try {
            produtoEditar.setNome(String.valueOf(nomeBanco.getText()));
            produtoEditar.setQuantidade(Integer.parseInt(quantidadeBanco.getText()));
            produtoEditar.setQuantidadeMinima(Integer.parseInt(quantidadeMinimaBanco.getText()));
            produtoEditar.setValorCompra(Double.parseDouble(valorCompraBanco.getText()));
            produtoEditar.setValorVenda(Double.parseDouble(valorVendaBanco.getText()));
            System.out.println(produtoEditar.toString());
            Fachada.getInstance().produtoAlterar(produtoEditar);
            StartApp.getInstance().telaProduto(new Stage());
            StartApp.stageEditarProduto.close();
            produtoEditar = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
