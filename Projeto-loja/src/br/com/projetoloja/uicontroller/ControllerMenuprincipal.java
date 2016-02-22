package br.com.projetoloja.uicontroller;

import br.com.projetoloja.ui.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by João Henrique on 11/01/2016.
 */
public class ControllerMenuprincipal {

    @FXML
    private Label home;
    @FXML
    private Label cliente;
    @FXML
    private Label produto;
    @FXML
    private Label gerenciar;
    @FXML
    private Label ajuda;
    @FXML
    private Label sair;

    @FXML
    private void initialize(){
        Image image1 = new Image(getClass().getResourceAsStream("../imagens/home-icon.png"));
        Image image2 = new Image(getClass().getResourceAsStream("../imagens/cliente-icon.png"));
        Image image3 = new Image(getClass().getResourceAsStream("../imagens/produto-icon.png"));
        Image image4 = new Image(getClass().getResourceAsStream("../imagens/gerenciar-icon.png"));
        Image image5 = new Image(getClass().getResourceAsStream("../imagens/ajuda-icon.png"));
        Image image6 = new Image(getClass().getResourceAsStream("../imagens/close-icon.png"));
        ImageView homeImage = new ImageView(image1);
        ImageView clienteImage = new ImageView(image2);
        ImageView produtoImage = new ImageView(image3);
        ImageView gerenciarImage = new ImageView(image4);
        ImageView ajudaImage = new ImageView(image5);
        ImageView sairImage = new ImageView(image6);

        homeImage.setFitWidth(90);
        homeImage.setFitHeight(90);

        clienteImage.setFitWidth(90);
        clienteImage.setFitHeight(90);

        produtoImage.setFitWidth(90);
        produtoImage.setFitHeight(90);

        gerenciarImage.setFitWidth(90);
        gerenciarImage.setFitHeight(90);

        ajudaImage.setFitWidth(90);
        ajudaImage.setFitHeight(90);

        sairImage.setFitWidth(90);
        sairImage.setFitHeight(90);

        home.setGraphic(homeImage);
        cliente.setGraphic(clienteImage);
        produto.setGraphic(produtoImage);
        gerenciar.setGraphic(gerenciarImage);
        ajuda.setGraphic(ajudaImage);
        sair.setGraphic(sairImage);
    }

    @FXML
    private void home(){
        try {
            if(StartApp.stageLogin == null) {
                closeTela();
                StartApp.getInstance().home(new Stage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gerenciar(){
        try {
            if(StartApp.stageLogin == null) {
                closeTela();
                StartApp.getInstance().gerenciar(new Stage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void telaCliente(){
        try {
            if(StartApp.stageLogin == null) {
            closeTela();
            StartApp.getInstance().telaCliente(new Stage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void telaProdutos(){
        try {
            if(StartApp.stageLogin == null) {
            closeTela();
            StartApp.getInstance().telaProduto(new Stage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajuda() {
        try {
            if(StartApp.stageLogin == null) {
           closeTela();
           StartApp.getInstance().telaAjuda(new Stage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sair() {
        StartApp.getInstance().closed();
    }

    public void closeTela(){
        if (StartApp.stageHome != null) StartApp.stageHome.close();
        if (StartApp.stageGerenciar != null) StartApp.stageGerenciar.close();
        if (StartApp.stageGerirCliente != null) StartApp.stageGerirCliente.close();
        if (StartApp.stageGerirProduto != null) StartApp.stageGerirProduto.close();
        if (StartApp.stageAjuda != null) StartApp.stageAjuda.close();
        if (StartApp.stageMaisdetalhesProduto != null) StartApp.stageMaisdetalhesProduto.close();
        if (StartApp.stageMaisdetalhesCliente != null) StartApp.stageMaisdetalhesCliente.close();
        if (StartApp.stageEditarProduto != null) StartApp.stageEditarProduto.close();
        if (StartApp.stageEditarCliente != null) StartApp.stageEditarCliente.close();
        if (StartApp.stageNovoProduto != null) StartApp.stageNovoProduto.close();
        if (StartApp.stageNovoCliente != null) StartApp.stageNovoCliente.close();
        if(StartApp.stagefinalizarCompra != null) StartApp.stagefinalizarCompra.close();
        if(StartApp.stageFinalizarPagamentoCarner !=null) StartApp.stageFinalizarPagamentoCarner.close();
        if(StartApp.stagePagamentoParcela !=null) StartApp.stagePagamentoParcela.close();
        if(StartApp.stagePagamentoLivre !=null) StartApp.stagePagamentoLivre.close();
        if(StartApp.stageTrocarPeca !=null) StartApp.stageTrocarPeca.close();
        if(StartApp.stageTrocarPecaCliente !=null) StartApp.stageTrocarPecaCliente.close();
        if(StartApp.stageGerarCarner !=null) StartApp.stageGerarCarner.close();        
    }
}
