package br.com.projetoloja.uicontroller;

import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.fachada.Fachada;
import br.com.projetoloja.ui.StartApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Henrique on 29/12/2015.
 */
public class ControllerCliente {

    List<Cliente> lista;

    @FXML
    private TableView tabela;
    @FXML
    private TableColumn codColuna;
    @FXML
    private TableColumn nomeColuna;
     @FXML
    private TableColumn ativoColuna;
    @FXML
    private TextField inputPesquisa;

    @FXML
    private void initialize(){
        try {
            lista = Fachada.getInstance().clienteListarTodos();
            for (Cliente c: lista) {
                System.out.println(c.toString());
            }
            codColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            ativoColuna.setCellValueFactory(new PropertyValueFactory<>("ativo"));

            tabela.setItems(FXCollections.observableArrayList(lista));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void pesquisa(){
        try {
            if(isNome()){
                lista = Fachada.getInstance().clientePesquisarPorNome(inputPesquisa.getText());
            }else{
                Cliente c = Fachada.getInstance().clientePesquisarPorId(Integer.parseInt(inputPesquisa.getText()));
                ArrayList<Cliente> listaCliente = new ArrayList<>();
                listaCliente.add(c);
                lista = listaCliente;
            }

            codColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            ativoColuna.setCellValueFactory(new PropertyValueFactory<>("ativo"));

            tabela.setItems(FXCollections.observableArrayList(lista));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void novo(){
        try {
            StartApp.getInstance().telaNovoCliente(new Stage());
            StartApp.stageGerirCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ativarDesativar(){
        try {
            Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
            System.out.println(c.toString());
            if(c.getAtivo() == 'A'){
                Fachada.getInstance().clienteRemover(c.getId());
            }else {
                Fachada.getInstance().clienteAtivar(c.getId());
            }
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void alterar(){
        try {
            Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
            c = Fachada.getInstance().clientePesquisarPorId(c.getId());
            ControllerEditarCliente.clienteEditar = c;
            StartApp.getInstance().editarCliente(new Stage());
            StartApp.stageGerirCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void maisDetalhes(){
        try {
            Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
           ControllerMaisDetalhesCliente.clienteMaisDetalhes = Fachada.getInstance().clientePesquisarPorId(c.getId());
            StartApp.getInstance().maisDetalhesCliente(new Stage());
            StartApp.stageGerirCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNome() {
        char [] nome = inputPesquisa.getText().toCharArray();
        boolean isNome = true;
        for(int i = 0; i <= nome.length -1; i++){
            if(nome[i] == '0' ||
                    nome[i] =='1' ||
                    nome[i] =='2' ||
                    nome[i] =='3' ||
                    nome[i] =='4' ||
                    nome[i] =='5' ||
                    nome[i] =='6' ||
                    nome[i] =='7' ||
                    nome[i] =='8' ||
                    nome[i] =='9'){
                isNome = false;
            }
        }
        return isNome;
    }

    @FXML
    private void pagamentoLivre() throws Exception{
        Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
        ControllerPagamentoLivre.clientePagamentoLivre = c;

        StartApp.getInstance().pagamentoParcelaLivre(new Stage());
        StartApp.stageGerirCliente.close();
    }

    @FXML
    private void trocarPeca() throws Exception {
        Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
        ControllerTrocarPecaCliente.clienteTrocar = c;

        StartApp.getInstance().trocarPeca(new Stage());
        StartApp.stageGerirCliente.close();
    }
    
    @FXML
    private void gerarCarner() throws Exception{
        Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
        ControllerGerarCarner.clienteGerarCarner = c;
        
        StartApp.getInstance().gerarCarner(new Stage());
        StartApp.stageGerirCliente.close();
    }
}
