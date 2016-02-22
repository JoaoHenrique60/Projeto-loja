package br.com.projetoloja.uicontroller;

import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by João Henrique on 12/01/2016.
 */
public class ControllerMaisDetalhesProduto {

    @FXML
    private Label idBanco;
    @FXML
    private Label nomeBanco;
    @FXML
    private Label quantidadeBanco;
    @FXML
    private Label quantidadeMinimaBanco;
    @FXML
    private Label valorCompraBanco;
    @FXML
    private Label valorVendaBanco;
    @FXML
    private Label ativoBanco;

    public static Produto produtoMaisDetalhes = null;

    @FXML
    private void initialize(){
        if(produtoMaisDetalhes != null){
            idBanco.setText(String.valueOf(produtoMaisDetalhes.getId()));
            nomeBanco.setText(produtoMaisDetalhes.getNome());
            quantidadeBanco.setText(String.valueOf(produtoMaisDetalhes.getQuantidade()));
            quantidadeMinimaBanco.setText(String.valueOf(produtoMaisDetalhes.getQuantidadeMinima()));
            valorCompraBanco.setText(String.valueOf(produtoMaisDetalhes.getValorCompra()));
            valorVendaBanco.setText(String.valueOf(produtoMaisDetalhes.getValorVenda()));
            ativoBanco.setText(String.valueOf(produtoMaisDetalhes.getAtivo()));
        }
    }

    @FXML
    public void voltar(){
        try {
            produtoMaisDetalhes = null;
            StartApp.getInstance().telaProduto(new Stage());
            StartApp.stageMaisdetalhesProduto.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
