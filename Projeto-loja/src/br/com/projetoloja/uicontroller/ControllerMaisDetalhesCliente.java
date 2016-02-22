package br.com.projetoloja.uicontroller;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.conta.Conta;
import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.produto.Produto;
import br.com.projetoloja.ui.StartApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by João Henrique on 15/01/2016.
 */
public class ControllerMaisDetalhesCliente {

    @FXML
    private Label nomeBanco;
    @FXML
    private Label telefoneBanco;
    @FXML
    private Label celularBanco;
    @FXML
    private Label cidadeBanco;
    @FXML
    private Label bairroBanco;
    @FXML
    private Label ruaBanco;
    @FXML
    private Label numeroBanco;
    @FXML
    private Label complementoBanco;
    @FXML
    private Label numeroContaBanco;
    @FXML
    private Label valorTotalBanco;
    @FXML
    private TableView tabela;
    @FXML
    private TableColumn nomeColuna;
    @FXML
    private TableColumn valorColuna;
    @FXML
    private TableColumn dataColuna;


    public static Cliente clienteMaisDetalhes = null;

    @FXML
    private void initialize() throws Exception {
        if(clienteMaisDetalhes != null){
            nomeBanco.setText(clienteMaisDetalhes.getNome());
            telefoneBanco.setText(clienteMaisDetalhes.getTelefone());
            celularBanco.setText(clienteMaisDetalhes.getCelular());
            cidadeBanco.setText(clienteMaisDetalhes.getEndereco().getCidade());
            bairroBanco.setText(clienteMaisDetalhes.getEndereco().getBairro());
            ruaBanco.setText(clienteMaisDetalhes.getEndereco().getRua());
            numeroBanco.setText(String.valueOf(clienteMaisDetalhes.getEndereco().getNumero()));
            complementoBanco.setText(clienteMaisDetalhes.getEndereco().getComplemento());
            numeroContaBanco.setText(String.valueOf(clienteMaisDetalhes.getConta().getId()));
            valorTotalBanco.setText(String.valueOf(clienteMaisDetalhes.getConta().getValorTotal()));

            Conta c = Fachada.getInstance().historiorConta(clienteMaisDetalhes.getConta().getId());
            List lista = c.getHistoricoConta();
            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            valorColuna.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
            dataColuna.setCellValueFactory(new PropertyValueFactory<>("data"));

            tabela.setItems(FXCollections.observableArrayList(lista));
        }
    }

    @FXML
    public void voltar(){
        try {
            clienteMaisDetalhes = null;
            StartApp.getInstance().telaCliente(new Stage());
            StartApp.stageMaisdetalhesCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void trocar(){
        try {
        	ControllerTrocarPecaCliente.clienteTrocar = clienteMaisDetalhes;
            StartApp.getInstance().trocarPecaCliente(new Stage());
            StartApp.stageMaisdetalhesCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
